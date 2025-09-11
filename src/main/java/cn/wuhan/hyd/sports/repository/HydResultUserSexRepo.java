package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultUserSex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 场馆预定-男女占比 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultUserSexRepo extends JpaRepository<HydResultUserSex, Long> {

    /**
     * 统计用户性别
     *
     * @return 包含 男、女 及 人数
     */
    @Query(value = "SELECT " +
            "sex AS gender, " +
            "sexNum AS genderCount " +
            "FROM hyd_result_user_sex WHERE YEAR(createdTime) = ?1 order by createdTime desc limit 2", nativeQuery = true)
    List<Map<String, Object>> countStadiumUserSexStat(String year);

    @Modifying
    @Query(value = "DELETE FROM hyd_result_user_sex WHERE batchNo != ?1 AND statisticalYear = ?2", nativeQuery = true)
    int deleteByNotBatchNo(String batchNo, Integer statisticalYear);
}
