package bluedragonvn.com.healmate.service.impl;

import bluedragonvn.com.healmate.dto.*;
import bluedragonvn.com.healmate.exception.SuccessCodeWithErrorResponse;
import bluedragonvn.com.healmate.exception.Error;
import bluedragonvn.com.healmate.exception.ErrorResponse;
import bluedragonvn.com.healmate.exception.RunTimeExceptionPlaceHolder;

import bluedragonvn.com.healmate.repository.RoleRepository;
import bluedragonvn.com.healmate.repository.UserRepository;
import bluedragonvn.com.healmate.repository.dao.Role;
import bluedragonvn.com.healmate.repository.dao.User;
import bluedragonvn.com.healmate.service.UserRoleService;
import bluedragonvn.com.healmate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author: phanh, Date : 3/5/2024
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public String createUser(CreateUserRequest createUserRequest) {

        if (userRepository.existsByPhone(createUserRequest.getPhone())) {
            throw new RunTimeExceptionPlaceHolder("Phone is already taken!!");
        }

        ErrorResponse errorResponse = ErrorResponse.builder()
                .uuid(UUID.randomUUID())
                .errors(new ArrayList<>())
                .build();

        List<Role> validRoles = new ArrayList<>();

        createUserRequest.getRoleNames().forEach(roleName -> {

            //if role exists add to list and persist, else add to error response persist valid roles and send
            // response containing invalid roles.
            roleRepository.findByRoleName(roleName).<Runnable>map(role -> () -> validRoles.add(role))
                    .orElse(() -> {
                        Error error = Error.builder()
                                .code("400")
                                .message(roleName + " role doesn't exist")
                                .build();
                        errorResponse.getErrors().add(error);
                    })
                    .run();
        });

        User user = User.builder()
                .phone(createUserRequest.getPhone())
                .roles(new HashSet<>(validRoles))
                .build();

        User savedUser = userRepository.save(user);

        if (!errorResponse.getErrors().isEmpty()) {
            throw new SuccessCodeWithErrorResponse(savedUser.getUserId(), errorResponse);
        }

        return savedUser.getUserId();
    }

    @Override
    public GetUserResponse getUserByPhone(String phone) {

        Optional<User> phoneOptional = userRepository
                .findByPhone(phone);
        User userByPhone = phoneOptional.orElseThrow(() ->
                new RunTimeExceptionPlaceHolder("Phone doesn't exist!!")
        );

        return GetUserResponse.builder()
                .userId(userByPhone.getUserId())
                .phone(userByPhone.getPhone())
                .roles(userByPhone.getRoles())
                .build();
    }

    @Override
    public GetUserResponse getUserByUserId(String userId) {
        Optional<User> userIdOptional = userRepository.findByUserId(userId);
        User userById = userIdOptional.orElseThrow(() ->
                new RunTimeExceptionPlaceHolder("UserName or Email doesn't exist!!")
        );

        return GetUserResponse.builder()
                .userId(userById.getUserId())
                .phone(userById.getPhone())
                .roles(userById.getRoles())
                .build();
    }

    @Override
    public GetUserInfoResponse getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String phone = (String) authentication.getPrincipal();

        GetUserResponse userByUserName = getUserByPhone(phone);

        return GetUserInfoResponse.builder()
                .userId(userByUserName.getUserId())
                .phone(userByUserName.getPhone())
                .build();

    }

    @Override
    public void updateUserInfo(UpdateUserRequest updateUserRequest) {

    }

    @Override
    public void deleteUserById(String userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String phone = (String) authentication.getPrincipal();
        GetUserResponse userByUserId = getUserByUserId(userId);

        if (phone.equals(userByUserId.getPhone())) {
            throw new RunTimeExceptionPlaceHolder("You cannot delete your own account!");
        }

        userRepository.deleteByUserId(userId);
    }

    @Override
    public List<GetUserResponse> getAllUsers() {

        Iterable<User> all = userRepository.findAll();
        List<GetUserResponse> allUsers = new ArrayList<>();
        all.iterator().forEachRemaining(u -> {
            GetUserResponse userResponse = GetUserResponse.builder()
                    .userId(u.getUserId())
                    .phone(u.getPhone())
                    .roles(u.getRoles())
                    .build();
            allUsers.add(userResponse);
        });

        return allUsers;
    }

    @Override
    public void updateUser(String userId, UpdateUserRequestFromAdmin updateUserRequestFromAdmin) {

        Optional<User> existingUser = userRepository.findByUserId(userId);

        User user = existingUser.orElseThrow(() ->
                new RunTimeExceptionPlaceHolder("UserId doesn't exist!!")
        );

        if (user.getRoles().size() > 0) {
            MapUserToRolesRequest mapUserToRolesRequest = new MapUserToRolesRequest();
            List<String> existingRoles = user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList());
            mapUserToRolesRequest.setRoleNames(existingRoles);
            userRoleService.removeRolesFromUser(user.getPhone(), mapUserToRolesRequest);
        }

        if (updateUserRequestFromAdmin.getRoles().size() > 0) {
            MapUserToRolesRequest mapUserToRolesRequest = new MapUserToRolesRequest();
            mapUserToRolesRequest.setRoleNames(updateUserRequestFromAdmin.getRoles());
            userRoleService.mapUserToRoles(user.getPhone(), mapUserToRolesRequest);
        }

        userRepository.save(user);
    }

}
