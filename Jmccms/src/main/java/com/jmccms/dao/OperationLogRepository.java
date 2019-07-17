package com.jmccms.dao;

import com.jmccms.entity.OperationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Description: 操作日志记录表dao层
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.jmccms.dao
 * @Author: ChenYongJia
 * @CreateTime: 2019-06-05 23:15
 * @Email chen87647213@163.com
 */
public interface OperationLogRepository extends JpaRepository<OperationLog, Integer>, JpaSpecificationExecutor<OperationLog> {



}
