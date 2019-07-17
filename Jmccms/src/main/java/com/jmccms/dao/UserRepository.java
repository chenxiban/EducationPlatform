package com.jmccms.dao;

import com.jmccms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Description: 用户接口
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.dao
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 18:05
 * @Email chen87647213@163.com
 */
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /**
     * 根据用户名和密码查询用户
     * @return
     */
    User findByUserNameAndUserPassWord(String userName, String userPassWord);

    /**
     * 根据用户名查询用户
     * @return
     */
    User findByUserName(String userName);

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    User findByUserId(Long userId);

    /**
     * 批量删除用户信息
     *
     * @param userList
     * @return
     */
    @Query(value = "DELETE FROM jmccms_user WHERE user_id IN (:userList)", nativeQuery = true)
    @Modifying
    @Transactional
    Integer deleteUser(@Param(value = "userList") List<String> userList);

    /**
     * 根据ID查询用户角色
     *
     * @param userId
     * @return
     */
    @Query(value = "SELECT role_id FROM jmccms_user_role WHERE 1=1 AND user_id=:usersId ", nativeQuery = true)
    List<Long> getUserRole(@Param(value = "usersId") Long userId);

}
