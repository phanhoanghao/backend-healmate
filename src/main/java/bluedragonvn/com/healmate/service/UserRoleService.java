package bluedragonvn.com.healmate.service;

import bluedragonvn.com.healmate.dto.MapRoleToUsersRequest;
import bluedragonvn.com.healmate.dto.MapUserToRolesRequest;

/**
 * @author: phanh, Date : 3/5/2024
 */
public interface UserRoleService {
    void mapUserToRoles(String phone, MapUserToRolesRequest mapUserToRolesRequest);

    void removeRolesFromUser(String phone, MapUserToRolesRequest mapUserToRolesRequest);

    void mapRoleToUsers(String roleName, MapRoleToUsersRequest mapRoleToUsersRequest);
}
