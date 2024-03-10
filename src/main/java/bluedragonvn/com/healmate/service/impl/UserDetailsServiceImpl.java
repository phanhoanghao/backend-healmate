package bluedragonvn.com.healmate.service.impl;

import bluedragonvn.com.healmate.dto.UserDetailsImpl;
import bluedragonvn.com.healmate.repository.UserRepository;
import bluedragonvn.com.healmate.repository.dao.User;
import bluedragonvn.com.healmate.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;


/**
 * @author: blued, Date: 3/5/2024
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	OTPService otpService;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
		User user = userRepository.findByPhone(phone)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with phone: " + phone));
		String otp = otpService.getCacheOtp(phone);
		return new org.springframework.security.core.userdetails.User(user.getPhone(), otp,
				new ArrayList<>());

	}
}