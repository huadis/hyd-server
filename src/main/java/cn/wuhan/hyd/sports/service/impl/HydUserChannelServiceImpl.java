package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydUserChannel;
import cn.wuhan.hyd.sports.repository.HydUserChannelRepository;
import cn.wuhan.hyd.sports.service.IHydUserChannelService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 功能说明： 场馆预定-用户来源渠道 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydUserChannelServiceImpl implements IHydUserChannelService {

    @Resource
    private HydUserChannelRepository hydUserChannelRepository;

    @Override
    public PageResult<HydUserChannel> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydUserChannel> queryAll() {
        return hydUserChannelRepository.findAll();
    }

    @Override
    @Transactional
    public HydUserChannel save(HydUserChannel hydUserChannel) {
        return hydUserChannelRepository.save(hydUserChannel);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydUserChannelRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydUserChannel update(HydUserChannel hydUserChannel) {
        if (hydUserChannel.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydUserChannelRepository.save(hydUserChannel);
    }

    @Override
    public HydUserChannel findById(Long id) {
        return hydUserChannelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    @Override
    public List<Map<String, Object>> countStadiumUserChannelStat() {
        return hydUserChannelRepository.countStadiumUserChannelStat();
    }

    /**
     * 批量保存 用户来源渠道
     *
     * @param userChannels 用户来源渠道 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydUserChannel> userChannels) {
        // 验证参数
        if (userChannels == null || userChannels.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (userChannels.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydUserChannel> savedList = hydUserChannelRepository.saveAll(userChannels);
        return savedList.size();
    }
}
