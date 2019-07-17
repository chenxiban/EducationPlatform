package com.jmccms.dao;

import com.jmccms.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Description: 用户详情接口
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.dao
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 18:06
 * @Email chen87647213@163.com
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>, JpaSpecificationExecutor<UserInfo> {

    /**
     * 根据用户名用户详情
     * @param userInfoName
     * @return
     */
    List<UserInfo> findByUserInfoNameLike(String userInfoName);

    /**
     * 根据用户名查询
     * @param userInfoName
     * @return
     */
    UserInfo findByUserInfoName(String userInfoName);

    /**
     * 根据用户id查询用户详情
     * @param userInfoId
     * @return
     */
    UserInfo findByUserInfoId(Long userInfoId);

    /**
     * 批量删除用户详情信息
     *
     * @param userInfoList
     * @return
     */
    @Query(value = "DELETE FROM jmccms_user_info WHERE user_info_id IN (:userInfoList)", nativeQuery = true)
    @Modifying
    @Transactional
    Integer deleteUserInfo(@Param(value = "userInfoList") List<String> userInfoList);

}
