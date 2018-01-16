package com.nsn.demo.entity;

import com.nsn.quick4j.orm.PKeyPolicy;
import com.nsn.quick4j.orm.annotation.Column;
import com.nsn.quick4j.orm.annotation.Entity;
import com.nsn.quick4j.orm.annotation.PKey;
import com.nsn.quick4j.orm.annotation.Table;

/**
 * 学生班级实体
 * @author donghao
 * @since 1.0
 */
@Entity
@Table("t_stuClass")
public class StuClass {

    @PKey(value = "c_id",pKeyPolicy = PKeyPolicy.UUID)
    private String cid;//主键 生成策略uuid
    @Column("c_className")
    private String className;//班级名称
    @Column("c_imagePath")
    private String imagePath;//班级图片路径

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
