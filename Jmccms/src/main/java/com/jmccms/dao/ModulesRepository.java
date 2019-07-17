package com.jmccms.dao;

import com.jmccms.entity.Modules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Description: 菜单模块接口
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.dao
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 18:02
 * @Email chen87647213@163.com
 */
public interface ModulesRepository extends JpaRepository<Modules, Integer>, JpaSpecificationExecutor<Modules> {

    /**
     * 根据根节点查询
     * @param id
     * @return
     */
    @Query(value = "SELECT modules_id,modules_create_time,modules_founder,modules_last_update_time,modules_name,modules_parent_id,modules_path,modules_update_man,modules_weight,modules_remark FROM jmccms_modules WHERE modules_parent_id=:parentId ORDER BY modules_weight ASC", nativeQuery = true)
    List<Modules> queryChildren(@Param("parentId") Integer parentId);

}
