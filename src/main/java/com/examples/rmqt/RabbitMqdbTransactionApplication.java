package com.examples.rmqt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

import com.examples.rmqt.beans.MyService;


/**
 * uncomment the implements and rename run2 to run, to run some tests as a normal application 
 * @author Arash Rajaeeyan
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.examples.rmqt"})
public class RabbitMqdbTransactionApplication {
// implements CommandLineRunner {

	 private static Logger LOG = LoggerFactory
		      .getLogger(RabbitMqdbTransactionApplication.class);
	 
	 @Autowired
	    JdbcTemplate jdbcTemplate;
	 
	@Autowired
	MyService service;
	
	public static void main(String[] args) {
		SpringApplication.run(RabbitMqdbTransactionApplication.class, args);
	}
	
//	@Override
	public void run2(String... args) {
        LOG.info("EXECUTING : command line runner");
        
		String sql = "insert into student values (" + 1 + " , '" + "Arash" + "');";
		int result = jdbcTemplate.update(sql);
		if (result==1)
		System.out.println(" -----> Student record inserted");

        service.operationDBFirst(false, false, 2, "arash");

    }
}
