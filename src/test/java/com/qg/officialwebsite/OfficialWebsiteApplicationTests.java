package com.qg.officialwebsite;

import com.qg.officialwebsite.domain.Enclosure;
import com.qg.officialwebsite.domain.News;
import com.qg.officialwebsite.domain.NewsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {JpaBaseConfiguration.class})
@SpringBootTest
public class OfficialWebsiteApplicationTests {

	@Autowired
	NewsRepository repository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void newsText(){
		News news = new News();
		List<Enclosure> enclosures = new ArrayList<>();
		news.setNewsTitle("震惊！！！");
		news.setNewsContext("裸死街头");
		news.setNewsTime(new Date());

		Enclosure enclosure = new Enclosure();
		enclosure.setContext("<h1>hello</h1>");
		enclosures.add(enclosure);

		repository.save(news);


	}
}
