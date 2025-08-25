package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydOriginOrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 订单访问接口 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Repository
public interface HydOriginOrderHistoryRepo extends JpaRepository<HydOriginOrderHistory, String> {

    @Query(value = "select district, count(*) num from (SELECT a.*, b.districtName as district from hyd_origin_order_history a, hyd_origin_tenant_history b WHERE a.tenantId = b.id) c GROUP BY c.district", nativeQuery = true)
    List<Map<String, Object>> districtStatCount();


    @Query(value = "select studentGender as gender, count(*) num from hyd_origin_order_history GROUP BY gender", nativeQuery = true)
    List<Map<String, Object>> genderStatCount();


    @Query(value = "select project, count(*) num from (SELECT a.*, b.stadiumItemName as project from hyd_origin_order_history a, hyd_origin_stadium_item_history b WHERE a.stadiumItemId = b.id) c GROUP BY c.project", nativeQuery = true)
    List<Map<String, Object>> projectStatCount();


    @Query(value = "select course, count(*) num from (SELECT a.*, b.courseName as course from hyd_origin_order_history a, hyd_origin_training_course_history b WHERE a.courseId = b.id) c GROUP BY c.course", nativeQuery = true)
    List<Map<String, Object>> courseStatCount();

    @Query(value = "\n" +
            "SELECT \n" +
            "    age_group AS ageGroup,\n" +
            "    COUNT(*) AS num\n" +
            "FROM (\n" +
            "    SELECT \n" +
            "        CASE \n" +
            "            WHEN userAge >= 0 AND userAge < 5 THEN '0-5岁'\n" +
            "            WHEN userAge >= 5 AND userAge < 10 THEN '5-10岁'\n" +
            "            WHEN userAge >= 10 AND userAge < 15 THEN '10-15岁'\n" +
            "            WHEN userAge >= 15 AND userAge < 20 THEN '15-20岁'\n" +
            "            WHEN userAge >= 20 THEN '20岁以上'\n" +
            "            ELSE '未知'\n" +
            "        END AS age_group\n" +
            "    FROM hyd_origin_order_history\n" +
            ") AS age_groups\n" +
            "GROUP BY age_group\n" +
            "ORDER BY \n" +
            "    CASE age_group\n" +
            "        WHEN '0-5岁' THEN 1\n" +
            "        WHEN '5-10岁' THEN 2\n" +
            "        WHEN '10-15岁' THEN 3\n" +
            "        WHEN '15-20岁' THEN 4\n" +
            "        WHEN '20岁以上' THEN 5\n" +
            "        WHEN '未知' THEN 6\n" +
            "    END;", nativeQuery = true)
    List<Map<String, Object>> userAgeStatCount();

    @Query(value = "select stadium, count(*) num from (SELECT a.*, b.stadiumName as stadium from hyd_origin_order_history a, hyd_origin_stadium_history b WHERE a.stadiumId = b.id) c GROUP BY c.stadium", nativeQuery = true)
    List<Map<String, Object>> stadiumStatCount();

    @Query(value = "select DISTINCT b.* from hyd_origin_order_history a, hyd_origin_stadium_history b where a.stadiumId = b.id", nativeQuery = true)
    List<Map<String, Object>> stadiumsByOrder();
}
