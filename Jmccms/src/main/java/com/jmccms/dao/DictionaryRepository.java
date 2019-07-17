package com.jmccms.dao;

import com.jmccms.entity.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Description: 字典信息Dao层
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.jmccms.dao
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-31 19:27
 * @Email chen87647213@163.com
 */
public interface DictionaryRepository extends JpaRepository<Dictionary, String>, JpaSpecificationExecutor<Dictionary> {

    /**
     * 根据字典类型名称模糊查询
     * @param userInfoName
     * @return
     */
    List<Dictionary> findBySdCodeLike(String sdCode);

}
