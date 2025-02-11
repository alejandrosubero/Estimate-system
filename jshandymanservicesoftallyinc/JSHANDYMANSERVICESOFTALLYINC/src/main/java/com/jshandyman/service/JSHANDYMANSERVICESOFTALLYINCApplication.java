package com.jshandyman.service;

import com.jshandyman.service.service.ClientService;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
//public class JSHANDYMANSERVICESOFTALLYINCApplication extends SpringBootServletInitializer {
	public class JSHANDYMANSERVICESOFTALLYINCApplication {

		protected static final Log logger = LogFactory.getLog(JSHANDYMANSERVICESOFTALLYINCApplication.class);

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
//		return application.sources(JSHANDYMANSERVICESOFTALLYINCApplication.class);
//	}

		public static void main(String[] args) {
			 SpringApplication.run(JSHANDYMANSERVICESOFTALLYINCApplication.class, args);
			// ConfigurableApplicationContext context = SpringApplication.run(JSHANDYMANSERVICESOFTALLYINCApplication.class, args);
			logger.info("START JSHANDYMAN SERVICES OF TALLY INC APPLICATION....");
	}


}

//TODO: saveUpdateEstimate ESTA FALLANDO EN ESTE METODO NO MANTIENE LOS VIEJOS D SERVICIO AL ENVIAR UNO NUEVO.
//TODO: QUE HACE CUANDO EN LA BASE NO HAY UN BILL in recursionUpdate(Estimate estimate, Estimate estimateBase).