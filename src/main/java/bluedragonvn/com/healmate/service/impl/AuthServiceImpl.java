package bluedragonvn.com.healmate.service.impl;

import bluedragonvn.com.healmate.dto.CreateOTPClientRequest;
import bluedragonvn.com.healmate.dto.CreateOTPClientResponse;
import bluedragonvn.com.healmate.exception.RunTimeExceptionPlaceHolder;
import bluedragonvn.com.healmate.repository.RoleRepository;
import bluedragonvn.com.healmate.repository.UserRepository;
import bluedragonvn.com.healmate.dto.CreateUserResponse;
import bluedragonvn.com.healmate.dto.SignUpRequest;
import bluedragonvn.com.healmate.repository.dao.Role;
import bluedragonvn.com.healmate.service.AuthService;
import bluedragonvn.com.healmate.service.OTPCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author: phanh, Date : 3/5/2024
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    OTPCacheServiceImpl otpCacheService;

    @Override
    public CreateOTPClientResponse createOTPClient(CreateOTPClientRequest createOTPClientRequest) {
        return null;// cần viết chỗ này để put vào redis
    }

    @Override
    public CreateUserResponse registerUser(SignUpRequest signUpRequest) {

        if (userRepository.existsByPhone(signUpRequest.getPhone())) {
            throw new RunTimeExceptionPlaceHolder("Phone is already taken!!");
        }


        // Creating user's account
         bluedragonvn.com.healmate.repository.dao.User user =
                new bluedragonvn.com.healmate.repository.dao.User(
                        signUpRequest.getPhone());

        Role userRole = roleRepository.findByRoleName("STANDARD_USER")
                .orElseThrow(() -> new RuntimeException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        bluedragonvn.com.healmate.repository.dao.User savedUser =
                userRepository.save(user);

        return CreateUserResponse.builder()
                .userId(savedUser.getUserId())
                .phone(savedUser.getPhone())
                .build();

    }
}
