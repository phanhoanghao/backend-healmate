package bluedragonvn.com.healmate.service;

import bluedragonvn.com.healmate.dto.CreateOTPClientRequest;
import bluedragonvn.com.healmate.dto.CreateOTPClientResponse;
import bluedragonvn.com.healmate.dto.CreateUserResponse;
import bluedragonvn.com.healmate.dto.SignUpRequest;

/**
 * @author: phanh, Date : 3/5/2024
 */
public interface AuthService {

    CreateOTPClientResponse createOTPClient(CreateOTPClientRequest createOTPClientRequest);

    CreateUserResponse registerUser(SignUpRequest signUpRequest);
}
