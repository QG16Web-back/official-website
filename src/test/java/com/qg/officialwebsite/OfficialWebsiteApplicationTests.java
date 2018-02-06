package com.qg.officialwebsite;

import com.qg.officialwebsite.domain.Group;
import com.qg.officialwebsite.domain.GroupMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OfficialWebsiteApplicationTests {

	@Autowired
	private GroupMapper groupMapper;

	@Test
	public void contextLoads() {
	}

}
