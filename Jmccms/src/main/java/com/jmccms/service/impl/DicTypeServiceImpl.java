package com.jmccms.service.impl;

import com.jmccms.dao.DicTypeRepository;
import com.jmccms.entity.DicType;
import com.jmccms.entity.Result;
import com.jmccms.service.DicTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 字典类业务实现类
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.jmccms.serviceimpl
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-31 19:32
 * @Email chen87647213@163.com
 */
@Slf4j
@Service
public class DicTypeServiceImpl implements DicTypeService {

    @Autowired
    private DicTypeRepository dicTypeRepository;

    /**
     * 根据dtid查询字典类型信息
     * @param dtId
     * @return
     */
    @Override
    public DicType selectById(String dtId) {
        log.info("进入根据dtid查询字典类型信息方法获得参数id:{}",dtId);
        return dicTypeRepository.getOne(dtId);
    }

    /**
     * 根据字典类型名查询
     * @param dtName
     * @return
     */
    @Override
    public List<DicType> findByDtNameLike(String dtName) {
        log.info("进入根据字典类型名查询方法获得参数dtName:{}",dtName);
        List<DicType> list=new ArrayList<>();
        try {
            list=dicTypeRepository.findByDtNameLike(dtName);
        } catch (NullPointerException e) {
            log.error("调用根据字典类型名查询数据为空",e);
        }
        return list;
    }

    /**
     * 添加字典类型
     * @param dicType
     * @return
     */
    @Override
    public Object addDicType(DicType dicType) {
        return null;
    }

    /**
     * 删除字典类型
     *
     * @param dicTypeList
     * @return
     */
    @Override
    public boolean delDicType(String dicTypeList) {
        return false;
    }

    /**
     * 修改字典类型
     *
     * @param dicType
     * @return
     */
    @Override
    public boolean updDicTypeById(DicType dicType) {
        return false;
    }


}
