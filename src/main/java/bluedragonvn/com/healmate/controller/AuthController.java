package bluedragonvn.com.healmate.controller;

import bluedragonvn.com.healmate.dto.*;
import bluedragonvn.com.healmate.service.OTPService;
import bluedragonvn.com.healmate.service.UserService;
import bluedragonvn.com.healmate.ulti.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * @author: blued, Date: 3/5/2024
 */
@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    OTPService otpService;
    @Autowired
    private UserService userService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;

    @RequestMapping(value = "/requestOtp", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<CreateOTPClientResponse> getOtp(@RequestBody OtpRequest otpRequest) {
        try {
            //generate OTP
            //String message = otpService.generateOtpVerification(otpRequest.getPhone());
            String message = "pending";
            CreateOTPClientResponse response = new CreateOTPClientResponse();
            response.setMessage(message);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            CreateOTPClientResponse response = new CreateOTPClientResponse();
            response.setMessage("Fail");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/verifyOtp", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<?> verifyOtp(@RequestBody CreateOTPClientRequest createOTPClientRequest) throws Exception {
        // chỗ này tạo tạm để test sau đó nếu cung cấp API SMS sẽ uncomment
        //if(otpService.checkOtpVerification(createOTPClientRequest.getPhone(), createOTPClientRequest.getOtp())) {
        if(createOTPClientRequest.getOtp().equals("1234")){
            otpService.clearOtp(createOTPClientRequest.getPhone());
            otpService.createTwilioOtpCache(createOTPClientRequest.getPhone(), createOTPClientRequest.getOtp());
            SignInResponse signInResponse = new SignInResponse();
            signInResponse.setMessage("Sucess");
            return new ResponseEntity<>(signInResponse, HttpStatus.OK);
        }
        SignInResponse signInResponse = new SignInResponse();
        signInResponse.setMessage("Incorrect phoneNo or verify code");
        return new ResponseEntity<>(signInResponse, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest authenticationRequest) throws Exception {
            if(otpService.getCacheOtp(authenticationRequest.getPhoneNo()).equals(authenticationRequest.getOtp())) {
                SignInResponse signInResponse = new SignInResponse();
                signInResponse.setMessage("Sucess");
                return new ResponseEntity<>(signInResponse, HttpStatus.OK);
            }
        SignInResponse signInResponse = new SignInResponse();
        signInResponse.setMessage("Incorrect phoneNo or verify code");
        return new ResponseEntity<>(signInResponse, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody CreateUserRequest createUserRequest) {
        if(userService.existByPhone(createUserRequest.getPhone())) {
            String message = "Error: Phone is already is use!";
            CreateOTPClientResponse response = new CreateOTPClientResponse();
            response.setMessage(message);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        userService.createUser(createUserRequest);
        try {
            //generate OTP
            //String message = otpService.generateOtpVerification(otpRequest.getPhone());
            String message = "User with phone number " +  createUserRequest.getPhone();
            CreateOTPClientResponse response = new CreateOTPClientResponse();
            response.setMessage(message);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            CreateOTPClientResponse response = new CreateOTPClientResponse();
            response.setMessage("Fail");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

}