package com.qg.officialwebsite.domain;

import org.apache.bcel.generic.NEW;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author:Wilder Gao
 * @time:2018/3/7
 * @Discription：
 */
@Repository
public interface NewsRepository extends JpaRepository<News , Integer> {
    @Query("SELECT t FROM news t ORDER BY t.newsTime DESC ")
    List<News> findNewsOrOrderByNewsTime();
}
