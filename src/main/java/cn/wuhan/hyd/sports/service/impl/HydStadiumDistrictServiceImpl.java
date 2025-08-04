package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydStadiumDistrict;
import cn.wuhan.hyd.sports.repository.HydStadiumDistrictRepository;
import cn.wuhan.hyd.sports.service.IHydStadiumDistrictService;
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
public class HydStadiumDistrictServiceImpl implements IHydStadiumDistrictService {

    @Resource
    private HydStadiumDistrictRepository hydStadiumDistrictRepository;

    @Override
    public PageResult<HydStadiumDistrict> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydStadiumDistrict> queryAll() {
        return hydStadiumDistrictRepository.findAll();
    }

    @Override
    @Transactional
    public HydStadiumDistrict save(HydStadiumDistrict hydStadiumDistrict) {
        return hydStadiumDistrictRepository.save(hydStadiumDistrict);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydStadiumDistrictRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydStadiumDistrict update(HydStadiumDistrict hydStadiumDistrict) {
        if (hydStadiumDistrict.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydStadiumDistrictRepository.save(hydStadiumDistrict);
    }

    @Override
    public HydStadiumDistrict findById(Long id) {
        return hydStadiumDistrictRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 统计各区场馆数量
     *
     * @return 包含区场馆统计数据列表，包含区名称及场馆数量
     */
    @Override
    public List<Map<String, Object>> countStadiumDistrict() {
        return hydStadiumDistrictRepository.countStadiumDistrict();
    }

    /**
     * 批量保存 在线场馆各区情况
     *
     * @param stadiumDistricts 在线场馆各区情况 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydStadiumDistrict> stadiumDistricts) {
        // 验证参数
        if (stadiumDistricts == null || stadiumDistricts.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (stadiumDistricts.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydStadiumDistrict> savedList = hydStadiumDistrictRepository.saveAll(stadiumDistricts);
        return savedList.size();
    }
}
