package com.jmccms.entity.weather;

import lombok.Data;

/**
 * @Description: 昨天实体类
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.jmccms.entity.weather
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-29 18:50
 * @Email chen87647213@163.com
 */
@Data
public class Yesterday {

    /**
     * 日期
     */
    private String date;

    /**
     * 高温温度
     */
    private String high;

    /**
     * 风向
     */
    private String fx;

    /**
     * 低温温度
     */
    private String low;

    /**
     * 风力
     */
    private String fl;

    /**
     * 天气类型
     */
    private String type;

}
