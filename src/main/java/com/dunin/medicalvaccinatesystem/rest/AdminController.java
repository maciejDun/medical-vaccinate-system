package com.dunin.medicalvaccinatesystem.rest;

import com.dunin.medicalvaccinatesystem.buissnessService.VaccinationService;
import com.dunin.medicalvaccinatesystem.model.restModel.VaccinatedUser;
import com.dunin.medicalvaccinatesystem.security.oauth.service.OAuth2AttributeExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AdminController.BASE_URL)
@RequiredArgsConstructor
public class AdminController {

    static final String BASE_URL = "/admin";

    private final OAuth2AttributeExtractor oAuth2AttributeExtractor;
    private final VaccinationService vaccinationService;

    @GetMapping("")
    public String returnAdmin() {
        return "hello admin "
                + oAuth2AttributeExtractor.getEmail();
    }

    @GetMapping("/vaccinated-users")
    public List<VaccinatedUser> getAllVaccinatedUsers() {
        return vaccinationService.getAllVaccinatedUsers();
    }

    @DeleteMapping("/vaccinated-users/{entityId}")
    public String deleteVaccinatedUser(@PathVariable Long entityId) {
        vaccinationService.unregisterUser(entityId);
        return "Successfully deleted vaccinated user";
    }

    @PostMapping("/vaccinated-users")
    public VaccinatedUser createVaccinatedUser(@RequestParam Long userId,@RequestParam Long termId) {
        return vaccinationService.addVaccinatedUser(userId, termId);
    }
}
