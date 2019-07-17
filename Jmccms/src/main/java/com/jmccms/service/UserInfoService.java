package com.jmccms.service;

import com.jmccms.entity.UserInfo;
import com.jmccms.entity.search.UserInfoSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Description: 用户详情表业务
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.service
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 18:15
 * @Email chen87647213@163.com
 */
public interface UserInfoService {

    /**
     * 分页多条件检索查询用户详情
     * @param userSearch
     * @param pageable
     * @return
     */
    Page<UserInfo> sreachByUser(UserInfoSearch userSearch, Pageable pageable);

    /**
     * 根据用户名模糊用户详情
     * @param userInfoName
     * @return
     */
    List<UserInfo> findByUserNameLike(String userInfoName);

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
     * 添加用户详情
     * @param userInfo
     * @return
     */
    Object addUserInfo(UserInfo userInfo);

    /**
     * 删除用户信息
     *
     * @param userInfoList
     * @return
     */
    boolean delUserInfo(String userInfoList);

    /**
     * 修改用户信息
     *
     * @param userInfo
     * @return
     */
    boolean updUserInfoById(UserInfo userInfo);

}
