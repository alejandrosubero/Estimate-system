package com.service.licenses;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
//public class licensesServiceApplication extends SpringBootServletInitializer  {
	public class licensesServiceApplication  {
		protected static final Log logger = LogFactory.getLog(licensesServiceApplication.class);

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
//		return application.sources(licensesServiceApplication.class);
//	}

		public static void main(String[] args) {
			SpringApplication.run(licensesServiceApplication.class, args);
		logger.info("the document  Swagger is in link: ==>  http://localhost:8987/service/swagger-ui.html");
	}

}

