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
    private String newsTitle;
    /**
     * 新闻内容
     */
    private String newsContext;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date newsTime;
    private int newsSurfNum;

    /**
     * 新闻附件
     */
    @ManyToMany(cascade = {} , fetch = FetchType.EAGER)
    @JoinTable
    private List<Enclosure> enclosures;

}
