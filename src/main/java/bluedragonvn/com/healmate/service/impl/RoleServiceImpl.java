package bluedragonvn.com.healmate.service.impl;

import bluedragonvn.com.healmate.dto.CreateRoleRequest;
import bluedragonvn.com.healmate.repository.RoleRepository;
import bluedragonvn.com.healmate.repository.dao.Role;
import bluedragonvn.com.healmate.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: phanh, Date : 3/5/2024
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;


    @Override
    public String createRole(CreateRoleRequest createRoleRequest) {

        Role role = Role.builder()
                .roleName(createRoleRequest.getRoleName())
                .roleDescription(createRoleRequest.getRoleDescription())
                .build();

        Role savedRole = roleRepository.save(role);
        return savedRole.getId();
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> allRoles = roleRepository.findAll();
        return allRoles;
    }
}