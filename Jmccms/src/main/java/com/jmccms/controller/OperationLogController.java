package com.jmccms.controller;

import com.jmccms.aspect.lang.annotation.LogRecord;
import com.jmccms.aspect.lang.enums.BusinessType;
import com.jmccms.aspect.lang.enums.OperatorType;
import com.jmccms.entity.OperationLog;
import com.jmccms.entity.search.OperationLogSearch;
import com.jmccms.service.OperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 操作日志控制器
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.jmccms.controller
 * @Author: ChenYongJia
 * @CreateTime: 2019-06-11 22:01
 * @Email chen87647213@163.com
 */
@Slf4j
@RestController
@RequestMapping(value = "/operationLog", name = "操作日志模块")
public class OperationLogController {

    @Autowired
    private OperationLogService operationLogService;

    /**
     * 查询所有操作日志记录模块 http://localhost:8066/jmccms/operationLog/getAllOperationLogs
     *
     * @param operationLogSearch
     * @return
     */
    @LogRecord(title = "查询所有操作日志记录模块", operatetype = OperatorType.SELECT, vistortype = BusinessType.MANAGE)
    @RequestMapping(value = "/getAllOperationLogs", name = "查询所有操作日志记录模块", method = RequestMethod.GET)
    public Map<String, Object> getAllOperationLogs(OperationLogSearch operationLogSearch) {
        log.info("进入查询所有操作日志记录模块方法控制器,operationLogSearch:{}", operationLogSearch.toString());

        Map<String, Object> map = null;
        try {
            Pageable pageable = PageRequest.of(operationLogSearch.getPage() - 1, operationLogSearch.getRows(), Sort.Direction.DESC,
                    "logOperatorId");
            Page<OperationLog> page = operationLogService.sreachByOperationLog(operationLogSearch, pageable);
            Long total = page.getTotalElements();
            List<OperationLog> list = page.getContent();

            map = new HashMap<String, Object>();
            map.put("total", total);
            map.put("rows", list);
        } catch (Exception e) {
            log.error("调用查询所有操作日志记录模块查询失败", e);
        }
        return map;
    }

}
