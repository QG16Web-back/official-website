package com.qg.officialwebsite.domain;

import javax.persistence.*;

/**
 * Created by K Lin
 * Date: 2018/2/6.
 * Time: 17:14
 * Remember to sow in the spring.
 * Description : official-website
 */
@Entity
@Table(name = "achievement")
public class Achievement {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    /**
     * 所在页码
     */
    @Column(name = "page", unique = true, nullable = false)
    private Integer page;

    /**
     * 成就标题
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * 成就主体
     */
    @Column(name = "body", nullable = false)
    private String body;

    public Achievement() {
    }

    public Achievement(Integer page, String title, String body) {
        this.page = page;
        this.title = title;
        this.body = body;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Achievement{" +
                "id=" + id +
                ", page=" + page +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
