package com.jmccms.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmccms.entity.weather.WeatherResponse;
import com.jmccms.service.WeatherDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @Description: 天气数据接口实现类
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.jmccms.serviceimpl
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-29 18:51
 * @Email chen87647213@163.com
 */
@Slf4j
@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";

    private static final long TIME_OUT = 1800L;//1800s

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        return this.doGetWeather(uri);
    }

    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String uri = WEATHER_URI + "city=" + cityName;
        return this.doGetWeather(uri);
    }

    /**
     * 重构代码
     *
     * @param uri
     * @return
     */
    private WeatherResponse doGetWeather(String uri) {
        ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);

        //将接口返回的Json字符串转换成对象
        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse resp = null;
        String strBody = "";

        if (respString.getStatusCodeValue() == 200) {
            strBody = respString.getBody();
        }

        try {
            resp = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resp;
    }

}
