package org.javatop.usercenter.domian;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Leo
 * @date  2024-03-17 23:19
 * @version 1.0
 * @description :
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`user`")
public class User implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名称
     */
    @TableField(value = "username")
    private String username;

    /**
     * 用户账号
     */
    @TableField(value = "userAccount")
    private String useraccount;

    /**
     * 头像地址
     */
    @TableField(value = "avatarUrl")
    private String avatarurl;

    /**
     * 性别
     */
    @TableField(value = "gender")
    private Byte gender;

    /**
     * 用户密码
     */
    @TableField(value = "userPassword")
    private String userpassword;

    /**
     * 电话号码
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 用户状态（0-正常，1-封号）
     */
    @TableField(value = "userStatus")
    private Integer userstatus;

    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "updateTime")
    private Date updatetime;

    /**
     * 删除状态（0-正常，1-删除）
     */
    @TableField(value = "isDelete")
    private Byte isdelete;

    /**
     * 用户角色
     */
    @TableField(value = "userRole")
    private Byte userrole;

    private static final long serialVersionUID = 1L;
}