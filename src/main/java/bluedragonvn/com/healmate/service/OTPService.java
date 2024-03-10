package bluedragonvn.com.healmate.service;

public interface OTPService {
    String generateOtp(String phoneNo);
    String generateOtpVerification(String phoneNo);
    boolean checkOtpVerification(String phoneNo, String code);
    String getRandomOTP(String phoneNo);
    void createTwilioOtpCache(String phoneNo, String code);
    String getCacheOtp(String key);
    void clearOtp(String key);
}
