package com.jshandyman.service.serviceImplement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.jshandyman.service.configurations.Constant;
import com.jshandyman.service.controller.AuthoritiesCotroller;
import com.jshandyman.service.entitys.User;
import com.jshandyman.service.pojo.*;
import com.jshandyman.service.security.EncryptAES;
import com.jshandyman.service.security.logingTrack.entitys.LogingTrack;
import com.jshandyman.service.security.logingTrack.pojos.LogingTrackPojo;
import com.jshandyman.service.security.logingTrack.services.LogingTrackService;
import com.jshandyman.service.service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class AuthoritiesLogingServiceImplement implements AuthoritiesLogingService {

	private static final Log logger = LogFactory.getLog(AuthoritiesLogingServiceImplement.class);

	 @Autowired
	 private UserService userService;

	@Autowired
	TaxesAndPriceService taxesandpriceService;
	
	@Autowired
	private RestTemplateBuilder restTemplate;

	@Autowired
	private DataJshandyManServicesConfigServiceImplement dataJshandyMan;

	@Autowired
	private ParametersServices parametersServices;

	@Autowired(required = true)
	private EmailDataConfigService emailDataConfigService;

	@Autowired
	private EncryptAES encryptAES;

	@Autowired
	private LogingTrackService logingTrackService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private DashboardDataService dashboardDataService;


//	@Value("${AuthoritiesLoginUrl}")
//	private String urlLogin;

//	@Value("${AuthoritiesLogoutUrl}")
//	private String urlLogout;

	@Value("${saltAESKey}")
	private String saltAES;

	@Override
	public UserRecoveryPojo recoveryUserPregunta(String mail){

		User user = null;
		UserRecoveryPojo respuesta=null;
		try{
			user = userService.findByMail(mail);

			if (user != null ){
				respuesta = new UserRecoveryPojo();
				respuesta.setPregunta(user.getPregunta());
				respuesta.setMail(mail);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return respuesta;
	}


	@Override
	public LogingResponse recoverUserAndAccesForLogin(UserRecoveryPojo recovery){
		User user = null;
		LogingResponse login = null;
		try{
			user = userService.findByMail(recovery.getMail());
			String respuesta = encryptAES.decryptAES(recovery.getRespuesta());
			String respuestaEncript = encryptAES.encript(respuesta, saltAES);
			String respuestaDecrypt = encryptAES.decrypt(respuestaEncript, saltAES);

			if (user != null && user.getRespuesta().equals(respuestaDecrypt)){
				login = new LogingResponse();
				login =	this.authorizationFromLogin(new LoginAuthPojo(user.getUserName(), user.getPassword()));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return login;
	}


	@Override
	public LogingResponse authorizationFromLogin(LoginAuthPojo auth) {

		logger.info("start /authorizationFromLogin");

		Map<String, Object> map2 = new HashMap<>();
		LogingResponse login = new LogingResponse();
		ResponseEntity<String> response = null;
		String userName ="";

		try{
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			logger.info("start .../authorizationFromLogin/restTemplate");
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

			Map<String, Object> map = new HashMap<>();
			map.put("username", auth.getUsername());
			map.put("password", auth.getPassword());

			String urlLogin = parametersServices.findByClave("AuthoritiesLoginUrl").getValor();
			HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
			response = restTemplate.postForEntity(urlLogin, entity, String.class);
			login.setStatus(response.getStatusCodeValue());
			logger.info("start .../authorizationFromLogin/restTemplate/response");

		}catch (Exception e){
			e.printStackTrace();
		}

		if (response.getStatusCodeValue() == 200){
			logger.info("start .../authorizationFromLogin/restTemplate/response/Status = 200");
			DataJshandyManServicesConfigPojo dataJshandyManServices = null;
			User user = null;
			TaxesAndPricePojo infoTaxes = null;
			EmailDataConfigPojo emailData = null;
			String port = null;

			user =  userService.findByUserName(auth.getUsername());
			infoTaxes = taxesandpriceService.findByDescription(Constant.TAXES, user.getCompany());
			dataJshandyManServices = dataJshandyMan.getActive(user.getCompany());
			port = parametersServices.findValue(Constant.MAIL_CODE_PORT);

			if(user != null){
				logger.info("start .../authorizationFromLogin/restTemplate/response/Status=200/user=Ok/");
				login.setUserCode(user.getUserCode());
				login.setUsername(user.getRol());
				login.setCompany(user.getCompany());
				userName = user.getFullName();

				if (user.getImagen() != null){
					login.setUserImagen(user.getImagen());
				}
			}
			login.setConfigurationRedy(true);

			if(dataJshandyManServices != null){
				logger.info("start .../authorizationFromLogin/restTemplate/response/Status=200/user=Ok/dataJshandyManServices=OK");
				login.setData(dataJshandyManServices);
			}else{
				logger.info("start .../authorizationFromLogin/restTemplate/response/Status=200/user=Ok/dataJshandyManServices=faill");
				login.setConfigurationRedy(false);
			}

			if(infoTaxes != null){
				logger.info("start .../authorizationFromLogin/restTemplate/response/Status=200/user=Ok/dataJshandyManServices=OK/infoTaxes=Ok");
				login.setTaxes(infoTaxes.getTaxes());
			}else {
				logger.info("start .../authorizationFromLogin/restTemplate/response/Status=200/user=Ok/dataJshandyManServices=OK/infoTaxes=fail");
				login.setConfigurationRedy(false);
			}

			if (port != null ){
				logger.info("start .../authorizationFromLogin/restTemplate/response/Status=200/user=Ok/dataJshandyManServices=OK/infoTaxes=Ok/port=Ok");
//				emailData =	emailDataConfigService.findByPort(port);
				emailData =	emailDataConfigService.findByPortAndCompanys(port, user.getCompany());
				if(emailData != null && emailData.getIdemailconfig() == null){
					login.setConfigurationRedy(false);
				}
			}
		}
		logger.info("start .../authorizationFromLogin/restTemplate/response/Status=200/user=Ok/dataJshandyManServices=OK/infoTaxes=Ok/port=Ok/Headers");
		response.getHeaders().entrySet().forEach((k) -> {
			if (k.getKey().equals("Authorization")) {
				login.setAuthorization(k.getKey());
				login.setToken( k.getValue().stream().collect(Collectors.joining()));
			}
			map2.put(k.getKey(), k.getValue().stream().collect(Collectors.joining()));
		});

		logger.info("start .../authorizationFromLogin/restTemplate/response/Status=200/user=Ok/dataJshandyManServices=OK/infoTaxes=Ok/port=Ok/Headers/LogingTrackPojo");
		LogingTrackPojo findByToken = logingTrackService.findByCodeWorkAndActive(login.getUserCode(), true);

		if(findByToken == null){
			logingTrackService.saveLogingTrack(new LogingTrack(login.getUserCode(), login.getToken(), userName));
		}

		if(findByToken != null){
			if(logingTrackService.deleteLogingTrack(findByToken.getIdLogingTrack())){
				logingTrackService.saveLogingTrack(new LogingTrack(login.getUserCode(), login.getToken(), userName));
			}
		}
		logger.info("start .../authorizationFromLogin/restTemplate/response/Status=200/user=Ok/dataJshandyManServices=OK/infoTaxes=Ok/port=Ok/Headers/LogingTrackPojo/return");
		return login;
	}


	@Override
	public LogingResponse authorizationFromlogout() {
		String urlLogout = parametersServices.findByClave("AuthoritiesLogoutUrl").getValor();
		ResponseEntity responseEntity = restTemplate.build().getForEntity(urlLogout, String.class, 2);
		responseEntity.getHeaders().entrySet().forEach((k) -> {
			System.out.println("Key is:"+ k.getKey());
			System.out.println("Values are:"+k.getValue().stream().collect(Collectors.joining()));
		});
		LogingResponse login = new LogingResponse();
		return login;
	}

	public void getHeaders() {
		ResponseEntity responseEntity = restTemplate.build().getForEntity("http://localhost:8080/getEmployee/{id}", String.class, 2);
		responseEntity.getHeaders().entrySet().forEach((k) -> {
			System.out.println("Key is:"+ k.getKey());
			System.out.println("Values are:"+k.getValue().stream().collect(Collectors.joining()));
		});

	}
}

