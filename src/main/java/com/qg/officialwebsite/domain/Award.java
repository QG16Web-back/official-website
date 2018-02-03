package com.qg.officialwebsite.domain;

import javax.persistence.*;

/**
 * Created by K Lin
 * Date: 2018/2/2.
 * Time: 21:07
 * Remember to sow in the spring.
 * Description : official-website
 */
@Entity
@Table(name = "award")
public class Award {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    /**
     * 奖项id
     */
    @Column(name = "award_id", nullable = false)
    private Integer awardId;

    /**
     * 奖项名称
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 项目名称
     */
    @Column(name = "project", nullable = false)
    private String project;

    /**
     * 获奖时间
     */
    @Column(name = "time", nullable = false)
    private String time;

    /**
     * 获奖项目描述
     */
    @Column(name = "detail", nullable = false)
    private String detail;

    /**
     * 获奖组别
     */
    @Column(name = "a_group", nullable = false)
    private String group;

    public Award(){}

    public Award(Integer awardid, String name, String project, String time) {
        this.awardId = awardid;
        this.name = name;
        this.project = project;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAwardId() {
        return awardId;
    }

    public void setAwardId(Integer awardid) {
        this.awardId = awardid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Award{" +
                "id=" + id +
                ", awardid='" + awardId + '\'' +
                ", name='" + name + '\'' +
                ", project='" + project + '\'' +
                ", time='" + time + '\'' +
                ", detail='" + detail + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}
