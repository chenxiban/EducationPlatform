package com.jmccms.entity.weather;

import lombok.Data;

/**
 * @Description: 预测实体类
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.jmccms.entity.weather
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-29 18:49
 * @Email chen87647213@163.com
 */
@Data
public class Forecast {

    /**
     * 日期
     */
    private String date;

    /**
     * 高温温度
     */
    private String high;

    /**
     * 风力
     */
    private String fengli;

    /**
     * 低温温度
     */
    private String low;

    /**
     * 风向
     */
    private String fengxiang;

    /**
     * 天气类型
     */
    private String type;

}
