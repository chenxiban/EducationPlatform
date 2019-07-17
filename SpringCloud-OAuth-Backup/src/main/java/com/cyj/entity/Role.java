package com.cyj.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @Description: 角色实体类
 * @ClassName: Role.java
 * @author ChenYongJia
 * @Date 2018年12月04日 下午20:40:56
 * @Email 867647213@qq.com
 */
@SuppressWarnings("serial")
@Getter
@Setter
@AllArgsConstructor // 自动所有参数的构造方法方法
@NoArgsConstructor // 自动无参的构造方法方法
@Builder // 使用建造模型
@Entity
@Table(name = "tb_roles")
public class Role implements Serializable {//GrantedAuthority,
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@OrderBy
	@Column(columnDefinition = "int unsigned  COMMENT '角色id'")
	private Integer rolesId;
	@Column(columnDefinition = "varchar(50) NOT NULL COMMENT '角色姓名'  ")
	private String rolesName;
	@Column(columnDefinition = "varchar(1000) COMMENT '角色功能'  ")
	private String rolesExplain;
	@Column(columnDefinition = "datetime COMMENT '创建时间' ")
	private Date rolesCreatTime;
	@Column(columnDefinition = "timestamp COMMENT '最后一次修改时间'", nullable = false, updatable = false, insertable = false)
	private Timestamp rolesUpdateTime;
	@Column(columnDefinition = "varchar(50) COMMENT '角色英文体现'  ")
	private String rolesEname;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER) // 多对多关系
	@Cascade(value = { CascadeType.ALL }) // 级联关系
	@JoinTable(name = "tb_userroles", // 指定第三张中间表名称
			joinColumns = { @JoinColumn(name = "roles_id") }, // 本表主键roleId与第三张中间表user_role_tb的外键user_role_tb_role_id对应.本表与中间表的外键对应关系
			inverseJoinColumns = { @JoinColumn(name = "users_id") }) // 另一张表与中间表的外键的对应关系
	@NotFound(action = NotFoundAction.IGNORE) // NotFound : 意思是找不到引用的外键数据时忽略，NotFound默认是exception
	private Set<User> usersSet = new HashSet<User>();// 拥有该角色的所有用户集合

	// 关联权限
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade = javax.persistence.CascadeType.ALL) // 指定多对多关系
																						// //默认懒加载,只有调用getter方法时才加载数据
	@JoinTable(name = "tb_rolepermission", // 指定第三张中间表名称
			joinColumns = { @JoinColumn(name = "role_id") }, // 本表主键userId与第三张中间表user_role_tb的外键user_role_tb_user_id对应
			inverseJoinColumns = { @JoinColumn(name = "permission_id") }) // 多对多关系另一张表与第三张中间表表的外键的对应关系
	@NotFound(action = NotFoundAction.IGNORE) // NotFound : 意思是找不到引用的外键数据时忽略，NotFound默认是exception
	private Set<Permission> roleSet = new HashSet<Permission>();// 用户所拥有的角色集合

	/*@Override
	public String getAuthority() {
		return rolesName;
	}

	@Override
	public String toString() {
		return "Role{" + "rolesId=" + rolesId + ", rolesName='" + rolesName + '\'' + '}';
	}*/
}
