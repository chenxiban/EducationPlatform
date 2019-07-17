package com.jmccms.service;

import com.jmccms.entity.OperationLog;
import com.jmccms.entity.search.OperationLogSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Description: 操作日志记录业务层
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.jmccms.service
 * @Author: ChenYongJia
 * @CreateTime: 2019-06-05 23:16
 * @Email chen87647213@163.com
 */
public interface OperationLogService {

    /**
     * 分页多条件检索查询操作日志详情
     *
     * @param operationLogSearch
     * @param pageable
     * @return
     */
    Page<OperationLog> sreachByOperationLog(OperationLogSearch operationLogSearch, Pageable pageable);

    /**
     * 添加操作日志记录
     *
     * @param operationLog
     * @return
     */
    Object addOperationLog(OperationLog operationLog);

}
