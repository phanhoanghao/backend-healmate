package bluedragonvn.com.healmate.service;

import bluedragonvn.com.healmate.dto.CreateRoleRequest;
import bluedragonvn.com.healmate.repository.dao.Role;

import java.util.List;

/**
 * @author: phanh, Date : 3/5/2024
 */
public interface RoleService {

    String createRole(CreateRoleRequest createRoleRequest);

    List<Role> getAllRoles();
}
