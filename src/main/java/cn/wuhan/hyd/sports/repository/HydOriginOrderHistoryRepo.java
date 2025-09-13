package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydOriginOrderHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 功能说明： 订单访问接口 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Repository
public interface HydOriginOrderHistoryRepo extends JpaRepository<HydOriginOrderHistory, String> {

    /**
     * 各区机构数量
     * @return
     */
    @Query(value = "SELECT s.districtName, s.district, COUNT(DISTINCT s.id) as num FROM hyd_origin_stadium_history s INNER JOIN hyd_origin_order_history o ON s.id=o.stadiumId GROUP BY s.districtName, s.district ORDER BY num DESC", nativeQuery = true)
    List<Map<String, Object>> districtStatCount();

    /**
     * 学生男女比例
     * @return
     */
    @Query(value = "select studentGender as gender, count(*) num from hyd_origin_order_history where studentGender is not null and studentGender != '' GROUP BY gender", nativeQuery = true)
    List<Map<String, Object>> genderStatCount();


    @Query(value = "select project, count(*) num from (SELECT b.stadiumItemName as project FROM hyd_origin_order_history a, hyd_origin_stadium_item_history b WHERE a.stadiumItemId = b.id and b.stadiumItemName is not null and b.stadiumItemName != '' ) c GROUP BY c.project order by num desc", nativeQuery = true)
    List<Map<String, Object>> projectStatCount();


    @Query(value = "SELECT c.courseName as course, COUNT(DISTINCT o.id) as num FROM `hyd_origin_training_course_history` `c` INNER JOIN `hyd_origin_order_history` `o` ON `c`.`id`=`o`.`courseId` GROUP BY `c`.`id` ORDER BY `num` DESC LIMIT 5", nativeQuery = true)
    List<Map<String, Object>> courseStatCount();

    /*@Query(value = "SELECT " +
            "    age_group AS ageGroup, " +
            "    COUNT(*) AS num " +
            "FROM ( " +
            "    SELECT " +
            "        CASE " +
            "            WHEN studentAge >= 0 AND studentAge < 5 THEN '0-5岁' " +
            "            WHEN studentAge >= 5 AND studentAge < 10 THEN '5-10岁' " +
            "            WHEN studentAge >= 10 AND studentAge < 15 THEN '10-15岁' " +
            "            WHEN studentAge >= 15 AND studentAge < 20 THEN '15-20岁' " +
            "            WHEN studentAge >= 20 THEN '20岁以上' " +
            "            ELSE '未知' " +
            "        END AS age_group " +
            "    FROM hyd_origin_order_history " +
            ") AS age_groups " +
            "GROUP BY age_group " +
            "ORDER BY " +
            "    CASE age_group " +
            "        WHEN '0-5岁' THEN 1 " +
            "        WHEN '5-10岁' THEN 2 " +
            "        WHEN '10-15岁' THEN 3 " +
            "        WHEN '15-20岁' THEN 4 " +
            "        WHEN '20岁以上' THEN 5 " +
            "        WHEN '未知' THEN 6 " +
            "    END;", nativeQuery = true)*/
    @Query(value = "SELECT concat(studentAge, '岁') as ageGroup, COUNT(*) as num FROM `hyd_origin_order_history` GROUP BY `studentAge` ORDER BY `studentAge` ASC", nativeQuery = true)
    List<Map<String, Object>> userAgeStatCount();

    @Query(value = "select stadium, sum(orderAmount) as orderAmount from (SELECT a.orderAmount, b.stadiumName as stadium FROM  hyd_origin_order_history AS a, hyd_origin_stadium_history b WHERE a.stadiumId = b.id and b.stadiumName is not null and b.stadiumName != '' ) c GROUP BY c.stadium order by orderAmount desc", nativeQuery = true)
    List<Map<String, Object>> stadiumStatCount();

    @Query(value = "select DISTINCT b.* from hyd_origin_order_history a, hyd_origin_stadium_history b where a.stadiumId = b.id", nativeQuery = true)
    List<Map<String, Object>> stadiumsByOrder();

    @Query(value = "SELECT * FROM hyd_origin_order_history WHERE (:startTime IS NULL OR createdTime >= :startTime) " +
            "AND (:endTime IS NULL OR createdTime <= :endTime)",
            countQuery = "SELECT COUNT(*) FROM hyd_origin_order_history " +
                    "WHERE (:startTime IS NULL OR createdTime >= :startTime) AND (:endTime IS NULL OR createdTime <= :endTime)", nativeQuery = true)
    Page<HydOriginOrderHistory> findAllByTimeRange(Pageable pageable, @Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime);
}
