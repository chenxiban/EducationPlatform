package com.jmccms.entity.weather;

import lombok.Data;

import java.util.List;

/**
 * @Description: 天气实体类
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.jmccms.entity.weather
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-29 18:49
 * @Email chen87647213@163.com
 */
@Data
public class Weather {

    /**
     * 昨天日期
     */
    private Yesterday yesterday;

    /**
     * 城市
     */
    private String city;

    /**
     * 空气质量指数
     */
    private String aqi;

    /**
     * 天气预测
     */
    private List<Forecast> forecast;

    /**
     * 感冒
     */
    private String ganmao;

    /**
     * 温度
     */
    private String wendu;



}
