package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultUserChannel;
import cn.wuhan.hyd.sports.repository.HydResultUserChannelRepository;
import cn.wuhan.hyd.sports.service.IHydResultUserChannelService;
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
public class HydResultUserChannelServiceImpl implements IHydResultUserChannelService {

    @Resource
    private HydResultUserChannelRepository hydResultUserChannelRepository;

    @Override
    public PageResult<HydResultUserChannel> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydResultUserChannel> queryAll() {
        return hydResultUserChannelRepository.findAll();
    }

    @Override
    @Transactional
    public HydResultUserChannel save(HydResultUserChannel hydResultUserChannel) {
        return hydResultUserChannelRepository.save(hydResultUserChannel);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydResultUserChannelRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultUserChannel update(HydResultUserChannel hydResultUserChannel) {
        if (hydResultUserChannel.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydResultUserChannelRepository.save(hydResultUserChannel);
    }

    @Override
    public HydResultUserChannel findById(Long id) {
        return hydResultUserChannelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    @Override
    public List<Map<String, Object>> countStadiumUserChannelStat() {
        return hydResultUserChannelRepository.countStadiumUserChannelStat();
    }

    /**
     * 批量保存 用户来源渠道
     *
     * @param userChannels 用户来源渠道 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultUserChannel> userChannels) {
        // 验证参数
        if (userChannels == null || userChannels.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (userChannels.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydResultUserChannel> savedList = hydResultUserChannelRepository.saveAll(userChannels);
        return savedList.size();
    }
}
