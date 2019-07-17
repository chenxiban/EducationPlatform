package com.cyj.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author ChenYongJia
 * @Description: 用户实体类
 * @ClassName: User.java
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
@Table(name = "jmccms_user")
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OrderBy
    @Column(columnDefinition = "bigint(19) unsigned  COMMENT '用户id'")
    private Long userId;
    @Column(columnDefinition = "varchar(64) NOT NULL COMMENT '用户名称'  ")
    private String userName;
    @Column(columnDefinition = "varchar(100) NOT NULL COMMENT '用户密码'  ")
    private String userPassWord;
    @Column(columnDefinition = "datetime COMMENT '用户创建时间' ")
    private Date userCreateTime;
    @Column(columnDefinition = "datetime COMMENT '用户最后一次登录时间' ")
    private Date userLastLoginTime;
    @Column(columnDefinition = "timestamp COMMENT '最后一次修改时间'", nullable = false, updatable = false, insertable = false)
    private Timestamp userLastUpdateTime;
    @Column(columnDefinition = "varchar(64) NOT NULL COMMENT '创建人'  ")
    private String userFounder;
    @Column(columnDefinition = "varchar(64) COMMENT '修改人'  ")
    private String userUpdateMan;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER) // 指定多对多关系
    @Cascade(value = {CascadeType.ALL}) // 设置级联关系
    @JoinTable(name = "jmccms_user_role", // 指定第三张中间表名称
            joinColumns = {@JoinColumn(name = "user_id")}, // 本表主键userId与第三张中间表user_role_tb的外键user_role_tb_user_id对应
            inverseJoinColumns = {@JoinColumn(name = "role_id")}) // 多对多关系另一张表与第三张中间表表的外键的对应关系
    @NotFound(action = NotFoundAction.IGNORE) // NotFound : 意思是找不到引用的外键数据时忽略，NotFound默认是exception
    private Set<Role> roleSet = new HashSet<>();// 用户所拥有的角色集合

    @Transient
    private String Pass;

    public static void main(String[] args) {
        User user = new User();
        user.setUserName("小佳");
        user.setUserPassWord("cyj123");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // 加密
        String encodedPassword = passwordEncoder.encode(user.getPassword().trim());
        System.out.println(encodedPassword);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> oauth = new ArrayList<>();
        Set<Role> roles = this.getRoleSet();// 根据角色控制
        //Set<Permission> permissions=this.getPermissionSet();// 根据权限控制
        for (Role role : roles) {
            oauth.add(new SimpleGrantedAuthority(role.getRolesEname()));
        }
        System.out.println("当前用户拥有的权限为=====>" + oauth);
        return oauth;
    }

    @Override
    public String getPassword() {
        return this.userPassWord;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    /**
     * 账户是否未过期 指示用户的帐户是否已过期。过期的帐户无法验证。
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否未锁定 指示用户是否锁定或解锁。无法对锁定用户进行身份验证。
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示用户的凭据(密码)是否已过期。过期凭据阻止身份验证。
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 指示用户是否启用或禁用。无法对禁用用户进行身份验证。
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", passWord='" + userPassWord + '\'' +
                ", userCreateTime=" + userCreateTime +
                ", userLastLoginTime=" + userLastLoginTime +
                ", userLastUpdateTime=" + userLastUpdateTime +
                ", userFounder='" + userFounder + '\'' +
                ", userUpdateMan='" + userUpdateMan + '\'' +
                ", rolesSet=" + roleSet +
                ", Pass='" + Pass + '\'' +
                '}';
    }

}