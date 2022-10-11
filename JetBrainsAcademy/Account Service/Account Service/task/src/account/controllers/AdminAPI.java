package account.controllers;

import account.contracts.ModifyRoleCM;
import account.contracts.UserInfoCM;
import account.models.UserEntity;
import account.security.AuthorityOperation;
import account.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@Validated
public class AdminAPI {

    @Autowired
    AdminService adminService;

    @GetMapping("/user")
    public ResponseEntity<List<UserInfoCM>> getUsers() {
        return new ResponseEntity<>(adminService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/user/role")
    public ResponseEntity<UserInfoCM> modifyRole(@RequestBody ModifyRoleCM modifyRoleCM, @AuthenticationPrincipal UserEntity admin) {
        if(modifyRoleCM.getOperation() == AuthorityOperation.REMOVE
                && modifyRoleCM.getRole().equals("ADMINISTRATOR")
                && modifyRoleCM.getEmail().matches(admin.getUsername()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't remove ADMINISTRATOR role!");

        UserInfoCM response = adminService.modifyUserRole(modifyRoleCM);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/user/{email}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable @NotNull @Email String email, @AuthenticationPrincipal UserEntity admin) {
        if(admin.getUsername().equals(email))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't remove ADMINISTRATOR role!");

        adminService.deleteUser(email);

        Map<String, String> response = new LinkedHashMap<>();
        response.put("user", email);
        response.put("status", "Deleted successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
