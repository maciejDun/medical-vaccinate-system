package com.dunin.medicalvaccinatesystem.testController;

import com.dunin.medicalvaccinatesystem.oauthService.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private IAuthenticationFacade authenticationFacade;


    @GetMapping("/google")
    public String returnTest() {
//        String authorities =
//                authenticationFacade.getAuthentication().getName();

        return "Hello you successfully logged in using Google account id: " +
                authenticationFacade.getAuthentication().getName();
    }

    @GetMapping("admin")
    public String returnAdmin() {
        return "hello admin " +
                authenticationFacade.getAuthentication().getName();
    }

    @GetMapping("/")
    public String returnHome() {
        return "Welcome to the vaccination registration system " +
                authenticationFacade.getAuthentication().getName();
    }

}
