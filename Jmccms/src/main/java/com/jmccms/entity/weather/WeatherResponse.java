package com.jmccms.entity.weather;

import lombok.Data;

/**
 * @Description: 天气的反应实体类
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.jmccms.entity.weather
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-29 18:50
 * @Email chen87647213@163.com
 */
@Data
public class WeatherResponse {

    /**
     *  日期
     */
    private Weather data;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 排序降序
     */
    private String desc;

}
