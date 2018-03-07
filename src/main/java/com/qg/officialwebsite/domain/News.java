package com.qg.officialwebsite.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author:Wilder Gao
 * @time:2018/3/7
 * @Discription：新闻实体类
 */
@Entity
@Table(name = "news")
public class News {
    /**
     * 新闻Id,主键，自动递增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id" , unique = true , nullable = false)
    private Integer id;

    /**
     * 新闻标题
     */
    @Column(name = "news_title" , nullable = false)
    private String newsTitle;

    /**
     * 新闻内容
     */
    @Column(name = "news_context" , nullable = false)
    private String newsContext;

    /**
     * 新闻日期
     */
    @Column(name = "news_time" , nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date newsTime;

    /**
     * 新闻浏览人数
     */
    @Column(name = "news_surf_num")
    private Integer newsSurfNum;

    /**
     * 新闻附件
     */
    @ManyToMany(cascade = {} , fetch = FetchType.EAGER)
    @JoinTable(name = "news_enclosure" ,
                joinColumns = {@JoinColumn(name = "news_id")},
                inverseJoinColumns = {@JoinColumn(name = "enclosure_id")})
    private List<Enclosure> enclosures;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsContext() {
        return newsContext;
    }

    public void setNewsContext(String newsContext) {
        this.newsContext = newsContext;
    }

    public Date getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(Date newsTime) {
        this.newsTime = newsTime;
    }

    public Integer getNewsSurfNum() {
        return newsSurfNum;
    }

    public void setNewsSurfNum(Integer newsSurfNum) {
        this.newsSurfNum = newsSurfNum;
    }

    public List<Enclosure> getEnclosures() {
        return enclosures;
    }

    public void setEnclosures(List<Enclosure> enclosures) {
        this.enclosures = enclosures;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsContext='" + newsContext + '\'' +
                ", newsTime=" + newsTime +
                ", newsSurfNum=" + newsSurfNum +
                ", enclosures=" + enclosures +
                '}';
    }
}
