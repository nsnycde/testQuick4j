package com.nsn.demo.entity;

import com.nsn.quick4j.orm.annotation.*;

import java.util.Date;

/**
 * @author donghao
 * @since 1.0
 */
@Entity
@Table("t_student")
public class Student {

    @PKey("s_id")
    private int id;//学生id 主键生成策略 mysql自增

    @Column("s_name")
    private String name;//学生姓名

    @Column("s_sex")
    private int sex;//学生性别

    @Column("s_birthday")
    private Date birthday;//学生生日

    @Column("s_address")
    private String address;//学生地址

    @Column("s_classId")
    private String classId;//学生班级id

    @Extra
    private int age;//额外字段

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getS_id() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}
