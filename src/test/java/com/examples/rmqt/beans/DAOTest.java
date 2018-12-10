package com.examples.rmqt.beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.examples.rmqt.QueueConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { QueueConfig.class })
public class DAOTest {

	public JdbcTemplate jdbcTemplate;

	@Autowired
	DAO dao;

	@Test
	public void test() {
		int id = 100;
		int res = dao.write(id, "Arash");
		Student s = dao.find(id);
		assertEquals(1, res);
		assertNotNull(s);
		assertEquals(id, s.getId());
		assertEquals("Arash", s.getName());
	}
}
