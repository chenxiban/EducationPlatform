package com.cyj.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @Description: 权限实体类
 * @author Chenyongjia
 * @Date 2018-11-13 下午5:42:22
 * @Email chen867647213@163.com
 * 
 */
@Getter
@Setter
@AllArgsConstructor // 自动所有参数的构造方法方法
@NoArgsConstructor // 自动无参的构造方法方法
@Builder // 使用建造模型
@Entity
@Table(name = "jmccms_permission")
public class Permission implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@OrderBy // 数据加载顺序
	@Column(columnDefinition = "bigint(19) unsigned comment '权限ID'  ")
	private Long permissionId;
	@Column(columnDefinition = "varchar(50) NOT NULL comment '权限所属模块'  ") // 字符长度20
	private String permissionModule;
	@Column(columnDefinition = "varchar(50) NOT NULL comment '权限名称'  ") // 字符长度20
	private String permissionName;
	@Column(columnDefinition = "varchar(50) comment '权限资源对象'  ") // 字符长度20
	private String permissionValue;
	@Column(columnDefinition = "varchar(60) comment '权限资源路径'  ") // 字符长度20
	private String permissionUrl;
	@Column(columnDefinition = "timestamp COMMENT '最后一次修改时间'", nullable = false, updatable = false, insertable = false)
	private Timestamp permissionLastUpdateTime;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)                                      //指定多对多关系    //默认懒加载,只有调用getter方法时才加载数据
	@JoinTable(name="jmccms_rolepermission",                       //指定第三张中间表名称
			joinColumns={@JoinColumn(name="permission_id")}, //本表主键userId与第三张中间表user_role_tb的外键user_role_tb_user_id对应
			inverseJoinColumns={@JoinColumn(name="role_id")})  //多对多关系另一张表与第三张中间表表的外键的对应关系
	@NotFound(action = NotFoundAction.IGNORE)	//NotFound : 意思是找不到引用的外键数据时忽略，NotFound默认是exception
	private Set<Role> roleSet = new HashSet<>();//用户所拥有的角色集合
	
	@Transient
	private Integer id;// 节点ID，对加载远程数据很重要。
	@Transient
	private String text;// 显示节点文本。
	@Transient
	private String state;// 节点状态，'open' 或
							// 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点
	@Transient
	private Boolean checked;// 表示该节点是否被选中。
	@Transient
	private Map<String, Object> attributes;// 被添加到节点的自定义属性。
	@Transient
	private List<Permission> children;// 一个节点数组声明了若干节点。
	
}
