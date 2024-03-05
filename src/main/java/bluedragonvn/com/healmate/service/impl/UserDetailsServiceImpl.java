package bluedragonvn.com.healmate.service.impl;

import bluedragonvn.com.healmate.dto.CreateUserRequest;
import bluedragonvn.com.healmate.dto.GetUserResponse;
import bluedragonvn.com.healmate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author: blued, Date: 3/5/2024
 */
@Service
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserService repository;
    @Override
    public UserDetails loadUserByUsername(String phoneNo) throws UsernameNotFoundException {
        GetUserResponse user = repository.getUserByPhone(phoneNo);
        if(user==null){
            CreateUserRequest userRequest = new CreateUserRequest();
            userRequest.setPhone(phoneNo);
            repository.createUser(userRequest);
        }
        return new org.springframework.security.core.userdetails.User(user.getPhone(), "",
                new ArrayList<>());
    }
}
