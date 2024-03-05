package bluedragonvn.com.healmate.service;

public interface OTPService {
    String generateOtp(String phoneNo);
    String getRandomOTP(String phoneNo);
    String getCacheOtp(String key);
    void clearOtp(String key);
}
