package com.dunin.medicalvaccinatesystem.rest;

import com.dunin.medicalvaccinatesystem.buissnessService.VaccinationService;
import com.dunin.medicalvaccinatesystem.model.restModel.Term;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(TestController.BASE_URL)
public class TestController {

    static final String BASE_URL = "test";
    private final VaccinationService vaccinationService;

    //endpoint for Angular testing
    @GetMapping("/terms")
    public List<Term> returnTermsForEveryone() {
        return vaccinationService.getAllVaccinationTerms();
    }


}
