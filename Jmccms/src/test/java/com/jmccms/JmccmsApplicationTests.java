package com.jmccms;

import com.jmccms.entity.UserInfo;
import com.jmccms.service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JmccmsApplicationTests {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void addUserInfo() throws ParseException {
        Date a = sdf.parse("1998-01-02");
        UserInfo userInfo = new UserInfo();
        userInfo.setUserInfoName("admin");
        userInfo.setUserInfoEmail("chen867647213@163.com");
        userInfo.setUserInfoBirthday(a);
        userInfo.setUserInfoSex("0");
        userInfo.setUserInfoAddress("河南省洛阳市涧西区辛店镇辛店村");
        userInfo.setUserInfoPhone("15638589820");
        userInfo.setUserInfoFounder("陈小佳");
        //System.out.println(userInfoService.addUserInfo(userInfo));
    }

    @Test
    public void updUserInfo() throws ParseException {
        Date a = sdf.parse("1996-01-02");
        UserInfo userInfo = new UserInfo();
        Long aong = Long.parseLong("6");
        userInfo.setUserInfoId(aong);
        userInfo.setUserInfoName("郭士才");
        userInfo.setUserInfoEmail("chen867647213@163.com");
        userInfo.setUserInfoBirthday(a);
        userInfo.setUserInfoSex("0");
        userInfo.setUserInfoAddress("河南省洛阳市涧西区辛店镇辛店村");
        userInfo.setUserInfoPhone("15638589820");
        userInfo.setUserInfoUpdateMan("陈小佳");
        //System.out.println(userInfoService.updUserInfoById(userInfo));
    }

}
