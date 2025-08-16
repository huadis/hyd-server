package cn.wuhan.hyd.system.service.impl;

import cn.wuhan.hyd.framework.config.FileProperties;
import cn.wuhan.hyd.framework.exception.BadRequestException;
import cn.wuhan.hyd.framework.exception.EntityExistException;
import cn.wuhan.hyd.framework.exception.EntityNotFoundException;
import cn.wuhan.hyd.framework.utils.*;
import cn.wuhan.hyd.system.domain.User;
import cn.wuhan.hyd.system.dto.UserDto;
import cn.wuhan.hyd.system.dto.UserQueryReq;
import cn.wuhan.hyd.system.mapstruct.UserMapper;
import cn.wuhan.hyd.system.repository.UserRepository;
import cn.wuhan.hyd.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月12日 <br>
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private UserMapper userMapper;
    @Resource
    private FileProperties properties;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDto findById(long id) {
        User user = userRepository.findById(id).orElseGet(User::new);
        ValidationUtil.isNull(user.getId(), "User", "id", id);
        return userMapper.toDto(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(User resources) {
        if (userRepository.findByUsername(resources.getUsername()) != null) {
            throw new EntityExistException(User.class, "username", resources.getUsername());
        }
        if (userRepository.findByEmail(resources.getEmail()) != null) {
            throw new EntityExistException(User.class, "email", resources.getEmail());
        }
        if (userRepository.findByPhone(resources.getPhone()) != null) {
            throw new EntityExistException(User.class, "phone", resources.getPhone());
        }
        userRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(User resources) throws Exception {
        User user = userRepository.findById(resources.getId()).orElseGet(User::new);
        ValidationUtil.isNull(user.getId(), "User", "id", resources.getId());
        User user1 = userRepository.findByUsername(resources.getUsername());
        User user2 = userRepository.findByEmail(resources.getEmail());
        User user3 = userRepository.findByPhone(resources.getPhone());
        if (user1 != null && !user.getId().equals(user1.getId())) {
            throw new EntityExistException(User.class, "username", resources.getUsername());
        }
        if (user2 != null && !user.getId().equals(user2.getId())) {
            throw new EntityExistException(User.class, "email", resources.getEmail());
        }
        if (user3 != null && !user.getId().equals(user3.getId())) {
            throw new EntityExistException(User.class, "phone", resources.getPhone());
        }
        user.setUsername(resources.getUsername());
        user.setEmail(resources.getEmail());
        user.setEnabled(resources.getEnabled());
        user.setPhone(resources.getPhone());
        user.setNickName(resources.getNickName());
        user.setGender(resources.getGender());
        userRepository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Long> ids) {
        userRepository.deleteAllByIdIn(ids);
    }

    @Override
    public UserDto findByName(String userName) {
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new EntityNotFoundException(User.class, "name", userName);
        } else {
            return userMapper.toDto(user);
        }
    }

    @Override
    public UserDto getLoginData(String userName) {
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            return null;
        } else {
            return userMapper.toDto(user);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePass(String username, String encryptPassword) {
        userRepository.updatePass(username, encryptPassword, new Date());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> updateAvatar(MultipartFile multipartFile) {
        // 文件大小验证
        FileUtil.checkSize(properties.getAvatarMaxSize(), multipartFile.getSize());
        // 验证文件上传的格式
        String image = "gif jpg png jpeg";
        String fileType = FileUtil.getExtensionName(multipartFile.getOriginalFilename());
        if (fileType != null && !image.contains(fileType)) {
            throw new BadRequestException("文件格式错误！, 仅支持 " + image + " 格式");
        }
        User user = userRepository.findByUsername(SecurityUtils.getCurrentUsername());
        String oldPath = user.getAvatarPath();
        File file = FileUtil.upload(multipartFile, properties.getPath().getAvatar());
        user.setAvatarPath(Objects.requireNonNull(file).getPath());
        user.setAvatarName(file.getName());
        userRepository.save(user);
        if (StringUtils.isNotBlank(oldPath)) {
            FileUtil.del(oldPath);
        }
        @NotBlank String username = user.getUsername();
        return new HashMap<String, String>(1) {{
            put("avatar", file.getName());
        }};
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEmail(String username, String email) {
        userRepository.updateEmail(username, email);
    }

    @Override
    public PageResult<UserDto> queryAll(UserQueryReq criteria, Pageable pageable) {
        Page<User> page = userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(userMapper::toDto));
    }

    @Override
    public List<UserDto> queryAll(UserQueryReq criteria) {
        List<User> users = userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder));
        return userMapper.toDto(users);
    }

    @Override
    public void download(List<UserDto> queryAll, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (UserDto userDTO : queryAll) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("用户名", userDTO.getUsername());
            map.put("邮箱", userDTO.getEmail());
            map.put("状态", userDTO.getEnabled() ? "启用" : "禁用");
            map.put("手机号码", userDTO.getPhone());
            map.put("修改密码的时间", userDTO.getPwdResetTime());
            map.put("创建日期", userDTO.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCenter(User resources) {
        User user = userRepository.findById(resources.getId()).orElseGet(User::new);
        User user1 = userRepository.findByPhone(resources.getPhone());
        if (user1 != null && !user.getId().equals(user1.getId())) {
            throw new EntityExistException(User.class, "phone", resources.getPhone());
        }
        user.setNickName(resources.getNickName());
        user.setPhone(resources.getPhone());
        user.setGender(resources.getGender());
        userRepository.save(user);
    }

    @Override
    public void resetPwd(Set<Long> ids, String pwd) {
        List<User> users = userRepository.findAllById(ids);
        // 重置密码
        userRepository.resetPwd(ids, pwd);
    }
}
