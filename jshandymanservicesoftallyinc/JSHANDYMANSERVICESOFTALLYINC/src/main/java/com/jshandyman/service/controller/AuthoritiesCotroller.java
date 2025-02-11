package com.jshandyman.service.controller;

import com.jshandyman.service.JSHANDYMANSERVICESOFTALLYINCApplication;
import com.jshandyman.service.pojo.EntityRespone;

import com.jshandyman.service.pojo.LoginAuthPojo;

import com.jshandyman.service.pojo.UserRecoveryPojo;
import com.jshandyman.service.service.AuthoritiesLogingService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/AuthoritiesCotroller")
public class AuthoritiesCotroller {


    protected static final Log logger = LogFactory.getLog(AuthoritiesCotroller.class);

    @Autowired
    private AuthoritiesLogingService authoritiesLogingService;

    @RequestMapping(value = "/loginAuth", method = RequestMethod.POST, consumes="application/json")
       private EntityRespone loginAuth(@RequestBody LoginAuthPojo loging) {
        logger.info("/loginAuth request");
        List<Object> response = new ArrayList<Object>();
        try {
            response.add(authoritiesLogingService.authorizationFromLogin(loging));
            return new EntityRespone("", " ", response);
        } catch (Exception e) {
            logger.info("/loginAuth response"+ "Error: " + e);
            return new EntityRespone("Error: " + e, "Error ", response);
        }
    }

    @GetMapping("/logout1")
    private EntityRespone logout() {
        logger.info("/logout request");
        List<Object> response = new ArrayList<Object>();
        try {
            response.add(authoritiesLogingService.authorizationFromlogout());
            return new EntityRespone("", " ", response);
        } catch (Exception e) {
            return new EntityRespone("Error: " + e, "Error ", response);
        }
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        logger.info("/logout request");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout"; //You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }




//    @PostMapping(value = "/loginAuth/recovery/user/data", consumes = {"application/json"})
    @RequestMapping(value = "/loginAuth/recovery/user/data", method = RequestMethod.POST, consumes="application/json")
    private EntityRespone loginAuthRecoveryUserData(@RequestBody UserRecoveryPojo recovery) {
        List<Object> response = new ArrayList<Object>();
        try {
            response.add(authoritiesLogingService.recoveryUserPregunta(recovery.getMail()));
            return new EntityRespone("", " ", response);
        } catch (Exception e) {
            return new EntityRespone("Error: " + e, "Error ", response);
        }
    }

//    @PostMapping(value = "/loginAuth/recovery", consumes = {"application/json"})
    @RequestMapping(value = "/loginAuth/recovery", method = RequestMethod.POST, consumes="application/json")
    private EntityRespone loginAuthRecoverUserAndLogin(@RequestBody UserRecoveryPojo recovery) {
        List<Object> response = new ArrayList<Object>();
        try {
            response.add(authoritiesLogingService.recoverUserAndAccesForLogin(recovery));
            return new EntityRespone("", " ", response);
        } catch (Exception e) {
            return new EntityRespone("Error: " + e, "Error ", response);
        }
    }


}


