package com.qg.officialwebsite.web;

import com.qg.officialwebsite.domain.News;
import com.qg.officialwebsite.dto.Result;
import com.qg.officialwebsite.service.impl.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author:Wilder Gao
 * @time:2018/3/7
 * @Discription：和新闻有关的控制器
 */
@RestController
@RequestMapping("/news")
public class NewsController {

//    @Autowired
//    private NewsServiceImpl newsService;
//
//    @RequestMapping("/show")
//    public News showNews(@Param("pageNum")int pageNum ,
//                                       @Param("pageSize") int pageSize){
//        return null;
//    }
}
