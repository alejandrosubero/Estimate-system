package com.service.mail;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
//public class mailserviceApplication extends SpringBootServletInitializer {
public class mailserviceApplication {

    protected static final Log logger = LogFactory.getLog(mailserviceApplication.class);

//    @Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
//		return application.sources(mailserviceApplication.class);
//	}

    public static void main(String[] args) {
        SpringApplication.run(mailserviceApplication.class, args);
        logger.info("Start Mail Service Application...");
        logger.info("the document  Swagger is in link: ==>  http://localhost:1111/mailservice/swagger-ui.html");
    }
}

