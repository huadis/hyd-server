package cn.wuhan.hyd.system.repository;

import cn.wuhan.hyd.system.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Set;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月12日 <br>
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @return /
     */
    User findByUsername(String username);

    /**
     * 根据邮箱查询
     *
     * @param email 邮箱
     * @return /
     */
    User findByEmail(String email);

    /**
     * 根据手机号查询
     *
     * @param phone 手机号
     * @return /
     */
    User findByPhone(String phone);

    /**
     * 修改密码
     *
     * @param username              用户名
     * @param pass                  密码
     * @param lastPasswordResetTime /
     */
    @Modifying
    @Query(value = "update sys_user set password = ?2 , pwd_reset_time = ?3 where username = ?1", nativeQuery = true)
    void updatePass(String username, String pass, Date lastPasswordResetTime);

    /**
     * 修改邮箱
     *
     * @param username 用户名
     * @param email    邮箱
     */
    @Modifying
    @Query(value = "update sys_user set email = ?2 where username = ?1", nativeQuery = true)
    void updateEmail(String username, String email);

    /**
     * 根据Id删除
     *
     * @param ids /
     */
    void deleteAllByIdIn(Set<Long> ids);

    /**
     * 重置密码
     *
     * @param ids 、
     * @param pwd 、
     */
    @Modifying
    @Query(value = "update sys_user set password = ?2 where user_id in ?1", nativeQuery = true)
    void resetPwd(Set<Long> ids, String pwd);
}
