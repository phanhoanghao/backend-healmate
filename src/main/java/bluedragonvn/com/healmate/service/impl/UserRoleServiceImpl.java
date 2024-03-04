package bluedragonvn.com.healmate.service.impl;

import bluedragonvn.com.healmate.dto.MapUserToRolesRequest;
import bluedragonvn.com.healmate.dto.MapRoleToUsersRequest;
import bluedragonvn.com.healmate.exception.*;
import bluedragonvn.com.healmate.exception.Error;
import bluedragonvn.com.healmate.repository.RoleRepository;
import bluedragonvn.com.healmate.repository.UserRepository;
import bluedragonvn.com.healmate.repository.dao.Role;
import bluedragonvn.com.healmate.repository.dao.User;
import bluedragonvn.com.healmate.service.UserRoleService;
import bluedragonvn.com.healmate.exception.ErrorResponse;
import bluedragonvn.com.healmate.exception.RunTimeExceptionPlaceHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author: phanh, Date : 3/5/2024
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void mapUserToRoles(String phone, MapUserToRolesRequest mapUserToRolesRequest) {

        Optional<User> phoneOptional = userRepository
                .findByPhone(phone);

        User user = phoneOptional.orElseThrow(() ->
                new RunTimeExceptionPlaceHolder("Phone doesn't exist!!")
        );

        Set<Role> roles = user.getRoles();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .uuid(UUID.randomUUID())
                .errors(new ArrayList<>())
                .build();

        mapUserToRolesRequest.getRoleNames().forEach(roleName -> {
            //if role exists add to list and persist, else add to error response persist valid roles and send
            // response containing invalid roles.
            roleRepository.findByRoleName(roleName).<Runnable>map(role -> () -> roles.add(role))
                    .orElse(() -> {
                        Error error = Error.builder()
                                .code("400")
                                .message(roleName + " role doesn't exist!!")
                                .build();
                        errorResponse.getErrors().add(error);
                    })
                    .run();
        });

        user.setRoles(roles);

        userRepository.save(user);

        if (!errorResponse.getErrors().isEmpty()) {
            throw new SuccessCodeWithErrorResponse(errorResponse);
        }

    }

    @Override
    public void removeRolesFromUser(String phone, MapUserToRolesRequest mapUserToRolesRequest) {

        Optional<User>phonelOptional = userRepository
                .findByPhone(phone);

        User user = phonelOptional.orElseThrow(() ->
                new RunTimeExceptionPlaceHolder("UserNameOrEmail doesn't exist!!")
        );

        Set<Role> roles = user.getRoles();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .uuid(UUID.randomUUID())
                .errors(new ArrayList<>())
                .build();

        mapUserToRolesRequest.getRoleNames().forEach(roleName -> {
            //if role exists add to list and persist, else add to error response persist valid roles and send
            // response containing invalid roles.
            roleRepository.findByRoleName(roleName).<Runnable>map(role -> () -> roles.remove(role))
                    .orElse(() -> {
                        Error error = Error.builder()
                                .code("400")
                                .message(roleName + " role doesn't exist!!")
                                .build();
                        errorResponse.getErrors().add(error);
                    })
                    .run();
        });

        user.setRoles(roles);

        userRepository.save(user);

        if (!errorResponse.getErrors().isEmpty()) {
            throw new SuccessCodeWithErrorResponse(errorResponse);
        }
    }

    @Override
    public void mapRoleToUsers(String roleName, MapRoleToUsersRequest mapRoleToUsersRequest) {

        Role role = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role doesn't exist!!"));

        ErrorResponse errorResponse = ErrorResponse.builder()
                .uuid(UUID.randomUUID())
                .errors(new ArrayList<>())
                .build();

        mapRoleToUsersRequest.getPhones().forEach(phone -> {
            userRepository.findByPhone(phone).<Runnable>map(user -> () -> role.addUser(user))
                    .orElse(() -> {
                        Error error = Error.builder()
                                .code("400")
                                .message(phone + " phone Number doesn't exist!!")
                                .build();
                        errorResponse.getErrors().add(error);
                    })
                    .run();
        });

        roleRepository.save(role);

        if (!errorResponse.getErrors().isEmpty()) {
            throw new SuccessCodeWithErrorResponse(errorResponse);
        }
    }
}
