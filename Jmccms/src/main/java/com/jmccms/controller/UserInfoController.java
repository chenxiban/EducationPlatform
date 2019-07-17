package com.jmccms.controller;

import com.jmccms.aspect.lang.annotation.LogRecord;
import com.jmccms.aspect.lang.enums.BusinessType;
import com.jmccms.aspect.lang.enums.OperatorType;
import com.jmccms.entity.UserInfo;
import com.jmccms.entity.search.UserInfoSearch;
import com.jmccms.service.UserInfoService;
import com.jmccms.util.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 用户详情表控制器
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.controller
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 18:31
 * @Email chen87647213@163.com
 */
@Slf4j
@RestController
@RequestMapping(value = "/userInfo", name = "用户详情模块")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 查询所有用户模块 http://localhost:8066/jmccms/userInfo/getAllUserInfos
     *
     * @param userInfoSearch
     * @return
     */
    @LogRecord(title = "查询所有用户模块", operatetype = OperatorType.SELECT, vistortype = BusinessType.MANAGE)
    @RequestMapping(value = "/getAllUserInfos", name = "查询所有用户模块", method = RequestMethod.GET)
    public Map<String, Object> getAllUserInfos(HttpServletRequest request, UserInfoSearch userInfoSearch) {
        log.info("进入查询所有用户模块方法控制器,访问IP为ip{}", IpUtils.getIpAddr(request));

        Map<String, Object> map = null;
        try {
            Pageable pageable = PageRequest.of(userInfoSearch.getPage() - 1, userInfoSearch.getRows(), Sort.Direction.ASC,
                    "userInfoId");
            Page<UserInfo> page = userInfoService.sreachByUser(userInfoSearch, pageable);
            Long total = page.getTotalElements();
            List<UserInfo> list = page.getContent();

            map = new HashMap<>();
            map.put("total", total);
            map.put("rows", list);
        } catch (Exception e) {
            log.error("调用查询所有用户模块方法查询失败", e);
        }
        return map;
    }

    /**
     * 添加用户详情信息 http://localhost:8066/jmccms/userInfo/addUserInfos
     *
     * @param userInfo
     * @return
     */
    @LogRecord(title = "添加用户详情信息", operatetype = OperatorType.INSERT, vistortype = BusinessType.MANAGE)
    @RequestMapping(value = "/addUserInfos", name = "添加用户详情信息", method = RequestMethod.POST)
    public Object addUserInfos(@RequestBody UserInfo userInfo) {
        log.info("进入添加用户详情信息方法控制器");
        return userInfoService.addUserInfo(userInfo);
    }

    /**
     * 删除用户详情信息 http://localhost:8066/jmccms/userInfo/delUserInfo
     *
     * @param userInfoId
     * @return
     */
    @LogRecord(title = "删除用户详情信息", operatetype = OperatorType.DELETE, vistortype = BusinessType.MANAGE)
    @RequestMapping(value = "/delUserInfo", name = "删除用户详情信息", method = RequestMethod.DELETE)
    public Object delUserInfo(@RequestParam(value = "userInfoId") String userInfoId) {// 有待修订
        log.info("进入删除用户详情信息方法控制器");
        return userInfoService.delUserInfo(userInfoId);
    }

    /**
     * 修改用户详情信息
     * http://localhost:8066/jmccms/userInfo/updUserInfo
     *
     * @param userInfo
     * @return
     */
    @LogRecord(title = "修改用户详情信息", operatetype = OperatorType.UPDATE, vistortype = BusinessType.MANAGE)
    @RequestMapping(value = "/updUserInfo", name = "修改用户详情信息", method = RequestMethod.PUT)
    public boolean updUserInfo(@RequestBody UserInfo userInfo) {
        log.info("进入修改用户详情信息方法控制器");
        return userInfoService.updUserInfoById(userInfo);
    }

    /**
     * 修改用户锁定状态 http://localhost:8066/jmccms/userInfo/lockUsers
     *
     * @param userInfo
     * @return
     */
    @LogRecord(title = "锁定用户", operatetype = OperatorType.UPDATE, vistortype = BusinessType.MANAGE)
    @RequestMapping(value = "/lockUsers", name = "锁定用户", method = RequestMethod.PUT)
    public boolean lockUsers(@RequestBody UserInfo userInfo) {
        log.info("进入锁定用户方法控制器");
        return userInfoService.updUserInfoById(userInfo);
    }

}
