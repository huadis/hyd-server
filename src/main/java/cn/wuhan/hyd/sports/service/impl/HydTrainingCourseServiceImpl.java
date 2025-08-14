package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydTrainingCourse;
import cn.wuhan.hyd.sports.repository.HydTrainingCourseRepo;
import cn.wuhan.hyd.sports.service.IHydTrainingCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 功能说明： 培训课程表业务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Service
@RequiredArgsConstructor
public class HydTrainingCourseServiceImpl implements IHydTrainingCourseService {

    private final HydTrainingCourseRepo hydTrainingCourseRepo;

    @Override
    public PageResult<HydTrainingCourse> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydTrainingCourse> queryAll() {
        return hydTrainingCourseRepo.findAll();
    }

    @Override
    public HydTrainingCourse findById(String id) {
        return hydTrainingCourseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("培训课程不存在，ID：" + id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydTrainingCourse save(HydTrainingCourse hydTrainingCourse) {
        return hydTrainingCourseRepo.save(hydTrainingCourse);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydTrainingCourse> hydTrainingCourses) {
        List<HydTrainingCourse> saved = hydTrainingCourseRepo.saveAll(hydTrainingCourses);
        return saved.size();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        hydTrainingCourseRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydTrainingCourse update(HydTrainingCourse hydTrainingCourse) {
        // 校验课程是否存在
        findById(hydTrainingCourse.getId());
        return hydTrainingCourseRepo.save(hydTrainingCourse);
    }
}
