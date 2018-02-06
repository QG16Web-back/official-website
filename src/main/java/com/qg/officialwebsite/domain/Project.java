package com.qg.officialwebsite.domain;

import javax.persistence.*;

/**
 * Created by K Lin
 * Date: 2018/2/6.
 * Time: 15:54
 * Remember to sow in the spring.
 * Description : official-website
 */
@Entity
@Table(name = "project")
public class Project {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    /**
     * 项目id
     */
    @Column(name = "project_id",nullable = false)
    private Integer projectid;

    /**
     * 项目名称
     */
    @Column(name = "name", nullable = false)
    private String name;

    public Project() {
    }

    public Project(Integer projectid, String name) {
        this.projectid = projectid;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectid=" + projectid +
                ", name='" + name + '\'' +
                '}';
    }
}
