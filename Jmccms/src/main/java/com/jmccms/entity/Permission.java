package com.jmccms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: 权限表
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.entity
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 17:43
 * @Email chen87647213@163.com
 */
@Getter
@Setter
@AllArgsConstructor // 自动所有参数的构造方法方法
@NoArgsConstructor // 自动无参的构造方法方法
@Builder
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
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")    //日期格式化为中国的时区 东8区
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")    //接受::字符串日期需要格式化为日期类型
    @Column(columnDefinition = "datetime COMMENT '创建时间'")
    private Date permissionCreateTime;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")    //日期格式化为中国的时区 东8区
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")    //接受::字符串日期需要格式化为日期类型
    @Column(columnDefinition = "timestamp COMMENT '最后一次修改时间'", nullable = false, updatable = false, insertable = false)
    private Timestamp permissionLastUpdateTime;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //指定多对多关系    //默认懒加载,只有调用getter方法时才加载数据
    @JoinTable(name = "jmccms_rolepermission",                       //指定第三张中间表名称
            joinColumns = {@JoinColumn(name = "permission_id")}, //本表主键userId与第三张中间表user_role_tb的外键user_role_tb_user_id对应
            inverseJoinColumns = {@JoinColumn(name = "role_id")})  //多对多关系另一张表与第三张中间表表的外键的对应关系
    @NotFound(action = NotFoundAction.IGNORE)    //NotFound : 意思是找不到引用的外键数据时忽略，NotFound默认是exception
    private Set<Role> roleSet = new HashSet<>();//用户所拥有的角色集合

    public Permission(String permissionValue, String permissionModule,
                      String permissionName) {
        super();
        this.permissionValue = permissionValue;
        this.permissionModule = permissionModule;
        this.permissionName = permissionName;
    }

    @Transient
    private Long id;// 节点ID，对加载远程数据很重要。

    @Transient
    private String label;// 显示节点文本。

    @Transient
    private List<Permission> children;

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId=" + permissionId +
                ", permissionModule='" + permissionModule + '\'' +
                ", permissionName='" + permissionName + '\'' +
                ", permissionValue='" + permissionValue + '\'' +
                ", permissionUrl='" + permissionUrl + '\'' +
                ", permissionCreateTime=" + permissionCreateTime +
                ", permissionLastUpdateTime=" + permissionLastUpdateTime +
                ", children=" + children +
                '}';
    }
}
