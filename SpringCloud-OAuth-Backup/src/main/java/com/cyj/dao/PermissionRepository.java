package com.cyj.dao;

import com.cxb.cyj.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 
 * @Description: 权限Dao接口
 * @ClassName: PermissionRepository.java
 * @author Chenyongjia
 * @Date 2018年11月12日 下午22:01
 * @Email 867647213@qq.com
 */
public interface PermissionRepository extends JpaRepository<Permission, Integer>, JpaSpecificationExecutor<Permission>  {
	
	
	
}
