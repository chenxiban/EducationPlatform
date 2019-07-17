package com.jmccms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
 * @Description: 菜单模块表
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.entity
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 15:55
 * @Email chen87647213@163.com
 */
@SuppressWarnings("serial")
@Getter
@Setter
@AllArgsConstructor // 自动所有参数的构造方法方法
@NoArgsConstructor // 自动无参的构造方法方法
@Builder
@Entity
@Table(name = "jmccms_modules")
public class Modules implements Serializable {

    /**
     * 模块自动增长主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OrderBy
    @Column(columnDefinition = "bigint(19) unsigned comment '备注:模块自动增长主键'  ")
    @JsonProperty(value = "id")
    private Integer modulesId;
    @Column(unique = true, columnDefinition = "varchar(50) comment '备注:模块名称'  ")
    @JsonProperty(value = "label")
    private String modulesName;
    @Column(columnDefinition = "int unsigned NOT NULL comment '备注:父模块编号'  ")
    private Long modulesParentId;
    @Column(columnDefinition = "varchar(120) comment '备注:模块路径'  ")
    private String modulesPath;
    @Column(columnDefinition = "int unsigned comment '备注:权重(建立了索引因为要排序优化)'  ")
    private Long modulesWeight;
    @Column(columnDefinition = "datetime comment '备注:模块创建时间'  ")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")	//日期格式化为中国的时区 东8区
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")	//接受::字符串日期需要格式化为日期类型
    private Date modulesCreateTime;
    @Column(columnDefinition = "TIMESTAMP comment '备注:模块最后一次修改时间'  ", nullable = false, updatable = false, insertable = false)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")	//日期格式化为中国的时区 东8区
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")	//接受::字符串日期需要格式化为日期类型
    private Timestamp modulesLastUpdateTime;
    @Column(columnDefinition = "varchar(50) comment '备注:创建人'  ")
    private String modulesFounder;
    @Column(columnDefinition = "varchar(50) comment '备注:修改人'  ")
    private String modulesUpdateMan;
    @Column(columnDefinition = "varchar(500) comment '备注:备注信息'  ")
    private String modulesRemark;

    // 关联角色
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = javax.persistence.CascadeType.ALL) // 指定多对多关系
    // //默认懒加载,只有调用getter方法时才加载数据
    @JoinTable(name = "jmccms_rolemodules", // 指定第三张中间表名称
            joinColumns = { @JoinColumn(name = "modules_id") }, // 本表主键userId与第三张中间表user_role_tb的外键user_role_tb_user_id对应
            inverseJoinColumns = { @JoinColumn(name = "role_id") }) // 多对多关系另一张表与第三张中间表表的外键的对应关系
    @NotFound(action = NotFoundAction.IGNORE) // NotFound : 意思是找不到引用的外键数据时忽略，NotFound默认是exception
    private Set<Role> rolesSet = new HashSet<>();// 用户所拥有的角色集合

    @Transient
    private boolean checked;

    @Transient
    private List<Modules> children;

    @Override
    public String toString() {
        return "Modules{" +
                "modulesId=" + modulesId +
                ", modulesName='" + modulesName + '\'' +
                ", modulesParentId=" + modulesParentId +
                ", modulesPath='" + modulesPath + '\'' +
                ", modulesWeight=" + modulesWeight +
                ", modulesCreateTime=" + modulesCreateTime +
                ", modulesLastUpdateTime=" + modulesLastUpdateTime +
                ", modulesFounder='" + modulesFounder + '\'' +
                ", modulesUpdateMan='" + modulesUpdateMan + '\'' +
                ", modulesRemark='" + modulesRemark + '\'' +
                ", rolesSet=" + rolesSet +
                ", checked=" + checked +
                '}';
    }
}
