package com.jshandyman.service.service;


import com.jshandyman.service.pojo.LoginAuthPojo;
import com.jshandyman.service.pojo.LogingResponse;
import com.jshandyman.service.pojo.UserRecoveryPojo;

public interface AuthoritiesLogingService {
	public LogingResponse recoverUserAndAccesForLogin(UserRecoveryPojo recovery);
	public UserRecoveryPojo recoveryUserPregunta(String mail);
	public LogingResponse authorizationFromLogin(LoginAuthPojo auth);
	public LogingResponse authorizationFromlogout();
}
