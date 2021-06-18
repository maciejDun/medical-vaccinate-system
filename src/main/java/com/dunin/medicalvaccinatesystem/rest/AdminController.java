package com.dunin.medicalvaccinatesystem.rest;

import com.dunin.medicalvaccinatesystem.buissnessService.VaccinationService;
import com.dunin.medicalvaccinatesystem.model.restModel.*;
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
    public String greetAdmin() {
        return "hello admin "
                + oAuth2AttributeExtractor.getEmail();
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return vaccinationService.getUsers();
    }

    @GetMapping("/not-registered-users")
    public List<User> getNotRegisteredUsers() {
        return vaccinationService.getNotRegisteredUsers();
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        vaccinationService.deleteUserById(userId);
        return "Successfully deleted user";
    }

    @PostMapping("/users")
    public User addUser(@Valid @RequestBody UserUpsert user) {
        return vaccinationService.addUser(user);
    }

    @PutMapping("/users")
    public User updateUser(@Valid @RequestBody UserUpsert user) {
        return vaccinationService.updateUser(user);
    }

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return vaccinationService.getRoles();
    }

    @DeleteMapping("/terms/{termId}")
    public String deleteTerm(@PathVariable Long termId) {
        vaccinationService.deleteTermById(termId);
        return "Successfully deleted term";
    }

    @GetMapping("/terms")
    public List<Term> getTerms() {
        return vaccinationService.getAllVaccinationTerms();
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
    public VaccinatedUser addVaccinatedUser(@RequestParam Long userId, @RequestParam Long termId) {
        return vaccinationService.addVaccinatedUser(userId, termId);
    }

    @GetMapping("/facility")
    public List<Facility> getFacilities() {
        return vaccinationService.getFacilities();
    }

    @DeleteMapping("/facility/{id}")
    public String deleteFacility(@PathVariable Long id) {
        vaccinationService.deleteFacilityById(id);
        return "Successfully deleted facility";
    }

    @PostMapping("/facility")
    public Facility addFacility(@Valid @RequestBody Facility facility) {
        return vaccinationService.addFacility(facility);
    }


}
