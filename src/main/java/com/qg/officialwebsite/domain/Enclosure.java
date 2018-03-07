package com.qg.officialwebsite.domain;

import javax.persistence.*;

/**
 * @author:Wilder Gao
 * @time:2018/3/7
 * @Discription：新闻附件实体类
 */
@Entity
@Table(name = "enclosure")
public class Enclosure {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id" , unique = true , nullable = false)
    private Integer id;

    @Column(name = "context" , nullable = false)
    private String context;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "Enclosure{" +
                "id=" + id +
                ", context='" + context + '\'' +
                '}';
    }
}
