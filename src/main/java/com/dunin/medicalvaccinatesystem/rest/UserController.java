package com.dunin.medicalvaccinatesystem.rest;

import com.dunin.medicalvaccinatesystem.buissnessService.VaccinationService;
import com.dunin.medicalvaccinatesystem.dao.vaccination.model.VaccinatedUserEntity;
import com.dunin.medicalvaccinatesystem.model.restModel.Term;
import com.dunin.medicalvaccinatesystem.security.oauth.service.OAuth2AttributeExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UserController.BASE_URL)
@RequiredArgsConstructor
public class UserController {

    static final String BASE_URL = "/user";

    private final OAuth2AttributeExtractor oAuth2AttributeExtractor;
    private final VaccinationService vaccinationService;

    @GetMapping("")
    public String returnWelcome() {
        return "Hello you successfully logged in using Google account id: "
                + oAuth2AttributeExtractor.getEmail();
    }

    @GetMapping("/terms")
    public List<Term> getTerms() {
        return vaccinationService.getAvailableVaccinationTerms();
    }

    @GetMapping("/terms/{termId}")
    public Term returnOneTerm(@PathVariable Long termId) {
        return vaccinationService.returnOneTerm(termId);
    }

    @GetMapping("/terms/register/{termID}")
    public VaccinatedUserEntity registerVaccUser(@PathVariable Long termID) {
        return vaccinationService.registerVaccUser(termID);
    }

    @DeleteMapping("/terms/unregister")
    public String unregisterUser() {
        vaccinationService.unregisterUser();
        return "You have been successfully unregistered";
    }
}
