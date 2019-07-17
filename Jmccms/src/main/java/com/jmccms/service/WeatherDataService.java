package com.jmccms.service;

import com.jmccms.entity.weather.WeatherResponse;

/**
 * @Description: 天气数据接口
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.jmccms.service
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-29 18:50
 * @Email chen87647213@163.com
 */
public interface WeatherDataService {

    /**
     * 根据城市Id查询天气
     *
     * @param cityId
     * @return
     */
    WeatherResponse getDataByCityId(String cityId);

    /**
     * 根据城市名称查询天气
     *
     * @param cityName
     * @return
     */
    WeatherResponse getDataByCityName(String cityName);

}
