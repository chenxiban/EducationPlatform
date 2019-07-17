package com.jmccms.service.impl;

import com.jmccms.dao.UserInfoRepository;
import com.jmccms.dao.UserRepository;
import com.jmccms.entity.Result;
import com.jmccms.entity.User;
import com.jmccms.entity.UserInfo;
import com.jmccms.entity.search.UserInfoSearch;
import com.jmccms.service.UserInfoService;
import com.jmccms.service.UserService;
import com.jmccms.util.IsEmptyUtils;
import com.jmccms.util.LocalDateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description: 用户详情表实现
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.serviceimpl
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 18:25
 * @Email chen87647213@163.com
 */
@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UserService userService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Calendar calendar = Calendar.getInstance();//日历对象

    /**
     * 分页多条件检索查询用户详情
     * @param userInfoSearch
     * @param pageable
     * @return
     */
    @Override
    public Page<UserInfo> sreachByUser(UserInfoSearch userInfoSearch, Pageable pageable){
        log.info("进入用户详情分页多条件检索查询方法，所得到的参数userInfoSearch为：{},,分页参数：pageable{}",userInfoSearch.toString(),pageable.toString());
        Page<UserInfo> userInfos=null;
        try {
            userInfos=userInfoRepository.findAll(this.getWhereClause(userInfoSearch), pageable);
            log.info("用户详情分页多条件检索查询方法为空检索成功");
        } catch (Exception e) {
            log.error("用户详情分页多条件检索查询方法,userInfos集合为空检索失败",e);
        }
        return userInfos;
    }

    /**
     * 根据用户名模糊查询用户详情
     * @param userInfoName
     * @return
     */
    @Override
    public List<UserInfo> findByUserNameLike(String userInfoName){
        log.info("进入根据用户名模糊查询用户详情方法，参数userInfoName为：{}",userInfoName);
        List<UserInfo>  userInfo= userInfoRepository.findByUserInfoNameLike(userInfoName);
        if(!IsEmptyUtils.isEmpty(userInfo) && userInfo.size()>0){
            return userInfoRepository.findByUserInfoNameLike(userInfoName);
        }else{
            log.info("根据userInfoName:"+userInfoName+",获取到的userInfo集合为空");
            return null;
        }

    }

    /**
     * 根据用户名查询
     * @param userInfoName
     * @return
     */
    @Override
    public UserInfo findByUserInfoName(String userInfoName){
        log.info("进入根据用户名查询用户详情方法，参数userInfoName为：{}",userInfoName);
        UserInfo  userInfo= userInfoRepository.findByUserInfoName(userInfoName);
        if(!IsEmptyUtils.isEmpty(userInfo)){
            return userInfo;
        }else{
            log.info("根据userInfoName:"+userInfoName+",获取到的userInfo集合为空");
            return null;
        }
    }

    /**
     * 根据用户id查询用户详情
     * @param userInfoId
     * @return
     */
    @Override
    public UserInfo findByUserInfoId(Long userInfoId){
        log.info("进入根据用户id查询用户信息方法,参数userInfoId为:{}",userInfoId);
        UserInfo userInfo=userInfoRepository.findByUserInfoId(userInfoId);
        if (!IsEmptyUtils.isEmpty(userInfo)) {
            return userInfo;
        } else {
            log.info("根据userInfoId:"+userInfoId+",查询用户详情为空查询失败");
            return null;
        }
    }

    /**
     * 添加用户详情
     * @param userInfo
     * @return
     */
    @Override
    public Object addUserInfo(UserInfo userInfo) {
        log.info("进入添加用户详情方法,获取到的userInfo参数为:{}",userInfo.toString());
        if(IsEmptyUtils.isEmpty(userInfo)){
            log.info("添加用户详情方法执行失败,参数userInfo为空");
            return new Result(false, "用户添加失败");
        }
        userInfo.setUserInfoCreateTime(new Date(System.currentTimeMillis()));
        userInfo.setUserInfoIsLookout("否");
        userInfo.setUserInfoPsdWrongTime(0);

        calendar.setTime(userInfo.getUserInfoBirthday());

        int year = calendar.get(Calendar.YEAR);//获取年份
        int month=calendar.get(Calendar.MONTH)+1;//获取月份
        int day=calendar.get(Calendar.DATE);//获取日

        userInfo.setUserInfoAge(LocalDateUtil.getAge(year,month,day));
        try {
            // 根据名称查询用户详情
            UserInfo userInfos = userInfoRepository.findByUserInfoName(userInfo.getUserInfoName());
            if (IsEmptyUtils.isEmpty(userInfos)) {
                UserInfo userInfo2 = userInfoRepository.saveAndFlush(userInfo);
                // 用于同时添加用户表数据
                User user=new User();
                user.setUserId(userInfo2.getUserInfoId());
                user.setUserName(userInfo2.getUserInfoName());
                user.setUserFounder(userInfo2.getUserInfoFounder());
                // 添加用户表信息
                userService.addUser(user);
                return new Result(true, "用户添加成功");
            }else{
                return new Result(false, "用户名重复添加失败");
            }
        } catch (Exception e) {
            log.error("添加用户详情失败",e);
            return new Result(false, "用户添加失败");
        }
    }

    /**
     * 删除用户信息
     *
     * @param userInfoId
     * @return
     */
    @Override
    public boolean delUserInfo(String userInfoId) {
        log.info("进入删除用户信息方法,获取的userInfoId参数为:{}",userInfoId);
        try {
            List<String> list = new ArrayList<>();
            String[] ids = userInfoId.split(",");
            for (String idArr : ids) {
                list.add(idArr);
            }
            userRepository.deleteUser(list);
            userInfoRepository.deleteUserInfo(list);
            return true;
        } catch (Exception e) {
            log.error("调用删除用户信息方法失败",e);
            return false;
        }
    }

    /**
     * 修改用户详情
     *
     * @param userInfo
     * @return
     */
    @Override
    public boolean updUserInfoById(UserInfo userInfo) {
        log.info("进入修改用户详情方法,获取到的参数userInfo为:{}",userInfo.toString());
        try {
            UserInfo userInfos=userInfoRepository.findByUserInfoId(userInfo.getUserInfoId());
            if(IsEmptyUtils.isEmpty(userInfos)){
                log.info("修改用户详情失败,根据传递的用户id查询的userInfos参数为空");
                return false;
            }
            User user=new User();
            // 用于获取年月日
            int year,month,day;
            // 判断用户详情姓名是否进行修改
            if (!IsEmptyUtils.isEmpty(userInfo.getUserInfoName())) {
                userInfos.setUserInfoName(userInfo.getUserInfoName());
                user.setUserName(userInfo.getUserInfoName());
                user.setUserId(userInfo.getUserInfoId());
                user.setUserUpdateMan(userInfo.getUserInfoUpdateMan());
                userService.updUser(user);
            }
            // 判断用户详情用户详细地址是否进行修改
            if (!IsEmptyUtils.isEmpty(userInfo.getUserInfoAddress())) {
                userInfos.setUserInfoAddress(userInfo.getUserInfoAddress());
            }
            // 判断用户详情用户电话是否进行修改
            if (!IsEmptyUtils.isEmpty(userInfo.getUserInfoPhone())) {
                userInfos.setUserInfoPhone(userInfo.getUserInfoPhone());
            }
            // 判断用户详情用户性别是否进行修改
            if (!IsEmptyUtils.isEmpty(userInfo.getUserInfoSex())) {
                userInfos.setUserInfoSex(userInfo.getUserInfoSex());
            }
            // 判断用户详情用户生日是否进行修改
            if (!IsEmptyUtils.isEmpty(userInfo.getUserInfoBirthday())) {
                calendar.setTime(userInfo.getUserInfoBirthday());

                year = calendar.get(Calendar.YEAR);//获取年份
                month=calendar.get(Calendar.MONTH)+1;//获取月份
                day=calendar.get(Calendar.DATE);//获取日
                // 同时修改用户年龄和用户生日
                userInfos.setUserInfoAge(LocalDateUtil.getAge(year,month,day));
                userInfos.setUserInfoBirthday(userInfo.getUserInfoBirthday());
            }
            // 判断用户详情用户邮箱地址是否进行修改
            if (!IsEmptyUtils.isEmpty(userInfo.getUserInfoEmail())) {
                userInfos.setUserInfoEmail(userInfo.getUserInfoEmail());
            }
            // 判断用户详情用户是否锁定是否进行修改
            if (!IsEmptyUtils.isEmpty(userInfo.getUserInfoIsLookout())) {
                userInfos.setUserInfoIsLookout(userInfo.getUserInfoIsLookout());
            }
            // 判断用户详情用户密码错误次数是否进行修改
            if (!IsEmptyUtils.isEmpty(userInfo.getUserInfoPsdWrongTime())) {
                userInfos.setUserInfoPsdWrongTime(userInfo.getUserInfoPsdWrongTime());
            }
            // 判断用户详情用户简介描述是否进行修改
            if (!IsEmptyUtils.isEmpty(userInfo.getUserInfoRemark())) {
                userInfos.setUserInfoRemark(userInfo.getUserInfoRemark());
            }
            // 判断用户详情用户修改人是否进行修改
            if (!IsEmptyUtils.isEmpty(userInfo.getUserInfoUpdateMan())) {
                userInfos.setUserInfoUpdateMan(userInfo.getUserInfoUpdateMan());
            }

            userInfos.setUserInfoId(userInfo.getUserInfoId());
            userInfoRepository.save(userInfos);
            return true;
        } catch (Exception e) {
            log.error("修改用户详情失败",e);
            return false;
        }
    }

    /**
     * 查询条件动态组装 动态生成where语句 匿名内部类形式
     *
     * @param us
     * @return
     * @author ChenYongJia
     */
    @SuppressWarnings({ "serial" })
    private Specification<UserInfo> getWhereClause(final UserInfoSearch us) {
        log.info("获得的UserInfoSearch参数为us:{}",us.toString());
        return new Specification<UserInfo>() {

            @Override
            public Predicate toPredicate(Root<UserInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();// 动态SQL表达式
                List<Expression<Boolean>> exList = predicate.getExpressions();// 动态SQL表达式集合

                // 用户名称检索
                if (!IsEmptyUtils.isEmpty(us.getUserInfoName())) {
                    exList.add(cb.like(root.<String>get("userInfoName"), "%" + us.getUserInfoName() + "%"));
                }
                // 用户是否锁定检索
                if (!IsEmptyUtils.isEmpty(us.getUserInfoIsLookout())) {
                    exList.add(cb.like(root.<String>get("userInfoIsLookout"),  us.getUserInfoIsLookout() ));
                }
                // 用户性别检索
                if (!IsEmptyUtils.isEmpty(us.getUserInfoSex())) {
                    exList.add(cb.like(root.<String>get("userInfoSex"),  us.getUserInfoSex() ));
                }
                // 生日检索
                if (!IsEmptyUtils.isEmpty(us.getBirthStart())) {
                    exList.add(cb.greaterThanOrEqualTo(root.<Date>get("userInfoBirthday"), us.getBirthdayStart()));
                }
                // 生日检索
                if (!IsEmptyUtils.isEmpty(us.getBirthdayEnd())) {
                    exList.add(cb.lessThanOrEqualTo(root.<Date>get("userInfoBirthday"), us.getBirthdayEnd()));
                }
                // 创建时间检索
                if (!IsEmptyUtils.isEmpty(us.getBirthStart())) {
                    exList.add(cb.greaterThanOrEqualTo(root.<Date>get("userInfoCreateTime"), us.getBirthStart()));
                }
                // 创建时间检索
                if (!IsEmptyUtils.isEmpty(us.getBirthEnd())) {
                    exList.add(cb.lessThanOrEqualTo(root.<Date>get("userInfoCreateTime"), us.getBirthEnd()));
                }
                // 用户邮箱检索
                if (!IsEmptyUtils.isEmpty(us.getUserInfoEmail())) {
                    exList.add(cb.like(root.<String>get("userInfoEmail"), "%" + us.getUserInfoEmail() + "%"));
                }
                // 用户手机号检索
                if (!IsEmptyUtils.isEmpty(us.getUserInfoPhone())) {
                    exList.add(cb.like(root.<String>get("userInfoPhone"), "%" + us.getUserInfoPhone() + "%"));
                }
                // 用户创建人检索
                if (!IsEmptyUtils.isEmpty(us.getUserInfoFounder())) {
                    exList.add(cb.like(root.<String>get("userInfoFounder"), "%" + us.getUserInfoFounder() + "%"));
                }
                // 用户地址检索
                if (!IsEmptyUtils.isEmpty(us.getUserInfoAddress())) {
                    exList.add(cb.like(root.<String>get("userInfoAddress"), "%" + us.getUserInfoAddress() + "%"));
                }
                // 用户状态检索
                if (!IsEmptyUtils.isEmpty(us.getUserInfoAddress())) {
                    exList.add(cb.like(root.<String>get("userInfoAddress"), "%" + us.getUserInfoAddress() + "%"));
                }
                return predicate;
            }

        };
    }

}
