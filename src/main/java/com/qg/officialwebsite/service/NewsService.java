package com.qg.officialwebsite.service;

import com.qg.officialwebsite.domain.News;
import com.qg.officialwebsite.dto.Result;

import java.util.List;

/**
 * @author:Wilder Gao
 * @time:2018/3/7
 * @Discription：
 */

public interface NewsService {

    /**
     * 查询新闻概况返回前端显示
     * @param pageNum 一页的数量
     * @param pageSize 请求第几页
     * @return 新闻的集合
     */
    Result<List<News>> showNewsHandler(int pageNum , int pageSize);
}
