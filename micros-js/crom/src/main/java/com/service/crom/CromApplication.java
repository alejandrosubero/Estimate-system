package com.service.crom;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CromApplication {
//	public class CromApplication extends SpringBootServletInitializer {

	protected static final Log logger = LogFactory.getLog(CromApplication.class);

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
//		return application.sources(CromApplication.class);
//	}

	public static void main(String[] args) {
		SpringApplication.run(CromApplication.class, args);
		logger.info("Start Aplication Crom");
	}

}
