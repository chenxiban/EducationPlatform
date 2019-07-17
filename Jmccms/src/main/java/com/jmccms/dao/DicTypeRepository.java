package com.jmccms.dao;

import com.jmccms.entity.DicType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Description: 字典类型Dao层
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.jmccms.dao
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-31 19:26
 * @Email chen87647213@163.com
 */
public interface DicTypeRepository extends JpaRepository<DicType, String>, JpaSpecificationExecutor<DicType> {

    /**
     * 根据字典类型名查询
     * @param dtName
     * @return
     */
    List<DicType> findByDtNameLike(String dtName);


}
