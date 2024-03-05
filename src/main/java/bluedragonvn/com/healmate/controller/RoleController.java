package bluedragonvn.com.healmate.controller;

import bluedragonvn.com.healmate.dto.CreateRoleRequest;
import bluedragonvn.com.healmate.repository.dao.Role;
import bluedragonvn.com.healmate.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * @author: phanh, Date : 3/5/2024
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/role")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<?> createRole(@RequestBody @Valid CreateRoleRequest createRoleRequest) {

        String userId = roleService.createRole(createRoleRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{roleId}")
                .buildAndExpand(userId).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/roles")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<?> getAllRoles() {
        List<Role> allRoles = roleService.getAllRoles();
        return ResponseEntity.ok(allRoles);

    }
}