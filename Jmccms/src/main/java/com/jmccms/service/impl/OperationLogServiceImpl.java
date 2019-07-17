package com.jmccms.service.impl;

import com.jmccms.dao.OperationLogRepository;
import com.jmccms.entity.OperationLog;
import com.jmccms.entity.search.OperationLogSearch;
import com.jmccms.service.OperationLogService;
import com.jmccms.util.IsEmptyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

/**
 * @Description: 操作日志记录实现类
 * @BelongsProject: EducationPlatform
 * @BelongsPackage: com.jmccms.service.impl
 * @Author: ChenYongJia
 * @CreateTime: 2019-06-05 23:19
 * @Email chen87647213@163.com
 */
@Slf4j
@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Autowired
    private OperationLogRepository operationLogRepository;

    /**
     * 分页多条件检索查询操作日志详情
     *
     * @param operationLogSearch
     * @param pageable
     * @return
     */
    @Override
    public Page<OperationLog> sreachByOperationLog(OperationLogSearch operationLogSearch, Pageable pageable) {
        log.info("进入用户详情分页多条件检索查询方法，所得到的参数operationLogSearch为：{},分页参数pageable:{}", operationLogSearch.toString(), pageable);
        Page<OperationLog> operationLog = null;
        try {
            operationLog = operationLogRepository.findAll(this.getWhereClause(operationLogSearch), pageable);
            log.info("用户详情分页多条件检索查询方法为空检索成功");
        } catch (Exception e) {
            log.error("用户详情分页多条件检索查询方法,userInfos集合为空检索失败", e);
        }
        return operationLog;
    }

    /**
     * 添加操作记录日志
     *
     * @param operationLog
     * @return
     */
    @Override
    public Object addOperationLog(OperationLog operationLog) {
        log.info("进入添加操作记录日志实现类方法,获得参数operationLog:{}", operationLog.toString());
        operationLog.setLogOperatingCreateTime(new Date());// 添加创建时间
        return operationLogRepository.save(operationLog);
    }

    /**
     * 查询条件动态组装 动态生成where语句 匿名内部类形式
     *
     * @param us
     * @return
     * @author ChenYongJia
     */
    @SuppressWarnings({"serial"})
    private Specification<OperationLog> getWhereClause(final OperationLogSearch us) {
        log.info("获得的OperationLogSearch参数为us:{}",us.toString());
        return new Specification<OperationLog>() {

            @Override
            public Predicate toPredicate(Root<OperationLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();// 动态SQL表达式
                List<Expression<Boolean>> exList = predicate.getExpressions();// 动态SQL表达式集合

                // 操作内容检索
                if (!IsEmptyUtils.isEmpty(us.getLogOperatingContent())) {
                    exList.add(cb.like(root.<String>get("logOperatingContent"), "%" + us.getLogOperatingContent() + "%"));
                }
                // 操作类型检索
                if (!IsEmptyUtils.isEmpty(us.getLogOperationType())) {
                    exList.add(cb.like(root.<String>get("logOperationType"), us.getLogOperationType()));
                }
                // 生日检索
                if (!IsEmptyUtils.isEmpty(us.getBirthStart())) {
                    exList.add(cb.greaterThanOrEqualTo(root.<Date>get("logOperatingCreateTime"), us.getBirthStart()));
                }
                // 生日检索
                if (!IsEmptyUtils.isEmpty(us.getBirthEnd())) {
                    exList.add(cb.lessThanOrEqualTo(root.<Date>get("logOperatingCreateTime"), us.getBirthEnd()));
                }
                // 创建时间检索
                if (!IsEmptyUtils.isEmpty(us.getLogOperatingResults())) {
                    exList.add(cb.like(root.<String>get("logOperatingResults"), "%" + us.getLogOperatingResults() + "%"));
                }

                return predicate;
            }

        };
    }

}
