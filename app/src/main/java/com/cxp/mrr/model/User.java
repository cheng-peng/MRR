package com.cxp.mrr.model;

/**
 * 文 件 名: User
 * 创 建 人: CXP
 * 创建日期: 2017-01-17 16:05
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Entity：将我们的java普通类变为一个能够被greenDAO识别的数据库类型的实体类
 * @Id：通过这个注解标记的字段必须是Long类型的，这个字段在数据库中表示它就是主键，并且它默认就是自增的
 * @Transient：表明这个字段不会被写入数据库，只是作为一个普通的java类字段，用来临时存储数据的，不会被持久化, 接下来让我们点击as中Build菜单栏中的Make Project，make完成之后会发现我们的User类中突然多了好多代码，这就是greenDAO自动为你生成的了，代码如下
 */
@Entity
public class User {

    @Id
    private Long id;
    private String userName;
    private String password;
    private String title;
    private String userImg;
    private Integer age;

    @Transient
    private int sex;

    @Generated(hash = 543439909)
    public User(Long id, String userName, String password, String title, String userImg, Integer age) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.title = title;
        this.userImg = userImg;
        this.age = age;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserImg() {
        return this.userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
