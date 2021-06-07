package com.dunin.medicalvaccinatesystem.rest;

import com.dunin.medicalvaccinatesystem.buissnessService.VaccinationService;
import com.dunin.medicalvaccinatesystem.dao.vaccination.model.VaccinatedUser;
import com.dunin.medicalvaccinatesystem.model.restModel.Term;
import com.dunin.medicalvaccinatesystem.security.oauth.service.OAuth2AttributeExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(UserController.BASE_URL)
@RequiredArgsConstructor
public class UserController {

    static final String BASE_URL = "/user";

    private final OAuth2AttributeExtractor oAuth2AttributeExtractor;
    private final VaccinationService vaccinationService;

    @GetMapping("")
    public String returnTest() {
        return "Hello you successfully logged in using Google account id: "
                + oAuth2AttributeExtractor.getEmail();
    }

    @GetMapping("terms")
    public List<Term> returnTerms() {
        return vaccinationService.getAllVaccinationTerms();
    }

    @GetMapping("/terms/{termId}")
    public Term returnOneTerm(@PathVariable Long termId) {
        return vaccinationService.returnOneTerm(termId);
    }

    @GetMapping("/terms/{termID}/register")
    public VaccinatedUser registerVaccUser(@PathVariable Long termID) {
        return vaccinationService.registerVaccUser(termID);
    }
}
