package com.dunin.medicalvaccinatesystem.rest;

import com.dunin.medicalvaccinatesystem.buissnessService.VaccinationService;
import com.dunin.medicalvaccinatesystem.model.restModel.Term;
import com.dunin.medicalvaccinatesystem.model.restModel.TermUpsert;
import com.dunin.medicalvaccinatesystem.model.restModel.User;
import com.dunin.medicalvaccinatesystem.model.restModel.VaccinatedUser;
import com.dunin.medicalvaccinatesystem.security.oauth.service.OAuth2AttributeExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/users")
    public List<User> returnUsers() {
        return vaccinationService.getUsers();
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        vaccinationService.deleteUserById(userId);
        return "Successfully deleted user";
    }

    @PostMapping("/users")
    public User addUser(@Valid @RequestBody User user){
        return vaccinationService.addUser(user);
    }

    @DeleteMapping("/terms/{termId}")
    public String deleteTerm(@PathVariable Long termId) {
        vaccinationService.deleteTermById(termId);
        return "Successfully deleted term";
    }

    @PostMapping("/terms")
    public Term addTerm(@Valid @RequestBody TermUpsert term) {
        return vaccinationService.addTerm(term);
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
