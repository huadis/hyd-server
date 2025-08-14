package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultStadiumDistrict;
import cn.wuhan.hyd.sports.repository.HydResultStadiumDistrictRepository;
import cn.wuhan.hyd.sports.service.IHydResultStadiumDistrictService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 功能说明： 场馆预定-在线场馆各区情况 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultStadiumDistrictServiceImpl implements IHydResultStadiumDistrictService {

    @Resource
    private HydResultStadiumDistrictRepository hydResultStadiumDistrictRepository;

    @Override
    public PageResult<HydResultStadiumDistrict> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydResultStadiumDistrict> queryAll() {
        return hydResultStadiumDistrictRepository.findAll();
    }

    @Override
    @Transactional
    public HydResultStadiumDistrict save(HydResultStadiumDistrict hydResultStadiumDistrict) {
        return hydResultStadiumDistrictRepository.save(hydResultStadiumDistrict);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydResultStadiumDistrictRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultStadiumDistrict update(HydResultStadiumDistrict hydResultStadiumDistrict) {
        if (hydResultStadiumDistrict.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydResultStadiumDistrictRepository.save(hydResultStadiumDistrict);
    }

    @Override
    public HydResultStadiumDistrict findById(Long id) {
        return hydResultStadiumDistrictRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 统计各区场馆数量
     *
     * @return 包含区场馆统计数据列表，包含区名称及场馆数量
     */
    @Override
    public List<Map<String, Object>> countStadiumDistrict() {
        return hydResultStadiumDistrictRepository.countStadiumDistrict();
    }

    /**
     * 批量保存 在线场馆各区情况
     *
     * @param stadiumDistricts 在线场馆各区情况 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultStadiumDistrict> stadiumDistricts) {
        // 验证参数
        if (stadiumDistricts == null || stadiumDistricts.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (stadiumDistricts.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydResultStadiumDistrict> savedList = hydResultStadiumDistrictRepository.saveAll(stadiumDistricts);
        return savedList.size();
    }
}
