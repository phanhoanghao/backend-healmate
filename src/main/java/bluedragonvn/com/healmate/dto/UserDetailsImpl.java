package bluedragonvn.com.healmate.dto;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import bluedragonvn.com.healmate.repository.dao.User;
import bluedragonvn.com.healmate.service.OTPService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {
	private OTPService otpService;
	private String key;
	private User user;

	public UserDetailsImpl(User user, OTPService otpService, String key)
	{
		this.user = user;
		this.otpService = otpService;
		this.key = key;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName()))
				.collect(Collectors.toList());

		return authorities;
	}

	@Override
	public String getPassword() {
		return otpService.getCacheOtp(key);
	}

	@Override
	public String getUsername() {
		return user.getPhone();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}