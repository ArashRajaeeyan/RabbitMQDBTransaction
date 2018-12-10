package com.examples.rmqt.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DAO {
	private static final Logger log = LoggerFactory.getLogger(DAO.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	public int write(int id, String name) {
		String sql = "insert into student values (" + id + " , '" + name + "')";
		int result = jdbcTemplate.update(sql);
		System.out.println("---> DB Write " + id + " " + name);
		return result;
	}

	public Student find(int id) {
		String sql = "SELECT id, name from student where id = ?";
		try {
			Student student = (Student) jdbcTemplate.queryForObject(sql, new Object[] { id },
					new BeanPropertyRowMapper(Student.class));
			return student;
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}
}
