package com.jmccms.controller;

import com.jmccms.aspect.lang.annotation.LogRecord;
import com.jmccms.aspect.lang.enums.BusinessType;
import com.jmccms.aspect.lang.enums.OperatorType;
import com.jmccms.entity.weather.WeatherResponse;
import com.jmccms.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 天气数据控制器
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.jmccms.controller
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-29 18:53
 * @Email chen87647213@163.com
 */
@RestController
@RequestMapping(value = "/weather", name = "天气数据获取")
public class WeatherController {

    @Autowired
    private WeatherDataService weatherDataService;

    /**
     * 根据城市id获取
     * 网关路径 http://localhost:3010/jmccms/Jmccms/weather/cityId/101280601
     * @param cityId
     * @return
     */
    @LogRecord(title = "根据城市id获取", operatetype = OperatorType.SELECT, vistortype = BusinessType.MANAGE)
    @RequestMapping(value = "/cityId/{cityId}", name = "根据城市id获取", method = RequestMethod.GET)
    public WeatherResponse getWeatherByCityId(@PathVariable("cityId") String cityId) {
        return weatherDataService.getDataByCityId(cityId);
    }

    /**
     * 根据城市名称获取
     * 网关路径 http://localhost:3010/jmccms/Jmccms/weather/cityName/上海
     * @param cityName
     * @return
     */
    @LogRecord(title = "根据城市名称获取", operatetype = OperatorType.SELECT, vistortype = BusinessType.MANAGE)
    @RequestMapping(value = "/cityName/{cityName}", name = "根据城市名称获取", method = RequestMethod.GET)
    public WeatherResponse getWeatherByCityName(@PathVariable("cityName") String cityName) {
        return weatherDataService.getDataByCityName(cityName);
    }

}
