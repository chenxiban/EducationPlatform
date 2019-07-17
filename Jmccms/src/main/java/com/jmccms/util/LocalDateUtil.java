package com.jmccms.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @Description: 计算年龄帮助类
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.util
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-03 22:24
 * @Email chen87647213@163.com
 */
public class LocalDateUtil {

    public static Long getAge(int year, int month, int day) {

        // 生日
        LocalDate birthday = LocalDate.of(year, month, day);

        // 当前日期
        LocalDate today = LocalDate.now();

        long duration = ChronoUnit.YEARS.between(birthday, today);

        return (Long)duration;
    }



}
