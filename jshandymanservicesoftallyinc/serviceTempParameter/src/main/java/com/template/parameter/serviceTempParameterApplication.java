package com.template.parameter;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class serviceTempParameterApplication {

		protected static final Log logger = LogFactory.getLog(serviceTempParameterApplication.class);
		public static void main(String[] args) {

		logger.info("the document  Swagger is in link: ==>  http://localhost:1111/template/swagger-ui.html");
			SpringApplication.run(serviceTempParameterApplication.class, args);
		logger.info("the document  Swagger is in link: ==>  http://localhost:1111/template/swagger-ui.html");
	}
}

