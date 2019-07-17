package com.jmccms.service;

import com.jmccms.entity.DicType;

import java.util.List;

/**
 * @Description: 字典类型业务层
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.jmccms.service
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-31 19:31
 * @Email chen87647213@163.com
 */
public interface DicTypeService {

    /**
     * 根据dtId查询字典信息
     * @param dtId
     * @return
     */
    DicType selectById(String dtId);

    /**
     * 根据字典类型名查询
     * @param dtName
     * @return
     */
    List<DicType> findByDtNameLike(String dtName);

    /**
     * 添加字典类型
     * @param dicType
     * @return
     */
    Object addDicType(DicType dicType);

    /**
     * 删除字典类型
     *
     * @param dicTypeList
     * @return
     */
    boolean delDicType(String dicTypeList);

    /**
     * 修改字典类型
     *
     * @param dicType
     * @return
     */
    boolean updDicTypeById(DicType dicType);


}
