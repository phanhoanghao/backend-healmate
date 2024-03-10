package bluedragonvn.com.healmate.ulti;

import bluedragonvn.com.healmate.dto.UserDetailsImpl;
import bluedragonvn.com.healmate.repository.UserRepository;
import bluedragonvn.com.healmate.repository.dao.User;
import bluedragonvn.com.healmate.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    OTPService otpService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByPhone(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with phone: " + userName));
        if (user == null) {
            throw new UsernameNotFoundException("User not Found !!");
        } else {
            return new UserDetailsImpl(user, otpService, userName);
        }

    }
}