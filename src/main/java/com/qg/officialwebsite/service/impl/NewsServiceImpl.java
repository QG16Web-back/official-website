package com.qg.officialwebsite.service.impl;

import com.github.pagehelper.PageHelper;
import com.qg.officialwebsite.domain.Enclosure;
import com.qg.officialwebsite.domain.News;
import com.qg.officialwebsite.domain.NewsRepository;
import com.qg.officialwebsite.dto.Result;
import com.qg.officialwebsite.enums.StateEnum;
import com.qg.officialwebsite.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author:Wilder Gao
 * @time:2018/3/7
 * @Discription：新闻服务类实现
 * 有事务回滚比较安全
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;

    @Override
    public Result<List<News>> showNewsHandler(int pageNum, int pageSize) {
        Result<List<News>> result ;
        if (pageNum <= 0 || pageSize <= 0){
            result = new Result<List<News>>(StateEnum.PAGE_NUM_ERROR);
            return result;
        }else {
            
        }
        return null;
    }
}
