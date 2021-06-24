package com.dunin.medicalvaccinatesystem.rest;

import com.dunin.medicalvaccinatesystem.buissnessService.VaccinationService;
import com.dunin.medicalvaccinatesystem.model.restModel.Term;
import com.dunin.medicalvaccinatesystem.model.restModel.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UserController.BASE_URL)
@RequiredArgsConstructor
public class UserController {

    static final String BASE_URL = "/user";

    private final VaccinationService vaccinationService;

    @GetMapping("")
    public User returnUser() {
        return vaccinationService.getLoggedInUser();
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
    public Term registerVaccUser(@PathVariable Long termID) {
        return vaccinationService.registerVaccUser(termID);
    }

    @DeleteMapping("/terms/unregister")
    public void unregisterUser() {
        vaccinationService.unregisterUser();
    }
}
