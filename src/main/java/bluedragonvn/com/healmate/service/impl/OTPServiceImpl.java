package bluedragonvn.com.healmate.service.impl;

import bluedragonvn.com.healmate.config.TwilioConfig;
import bluedragonvn.com.healmate.service.OTPService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: blued, Date: 3/5/2024
 */
@Component
public class OTPServiceImpl implements OTPService {
    private static final Integer EXPIRE_MIN = 3;
    private LoadingCache<String, String> otpCache;
    @Autowired
    private TwilioConfig twilioConfig;

    public OTPServiceImpl() {
        otpCache = CacheBuilder.newBuilder()
                .expireAfterWrite(EXPIRE_MIN, TimeUnit.MINUTES)
                .build(new CacheLoader<>() {
                    @Override
                    public String load(String s) {
                        return "";
                    }
                });
    }

    @Override
    public String generateOtp(String phoneNo) {
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
        PhoneNumber to = new PhoneNumber(phoneNo);
        PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());

        String otp = getRandomOTP(phoneNo);
        try {
            String otpMessage = "Dear Customer , Your OTP is " + otp + ". Use this otp to log in to Heal Mate Application";
            Message message = Message
                    .creator(to,
                            from,
                            otpMessage)
                    .create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return otp;
    }

    @Override
    public String generateOtpVerification(String phoneNo) {
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
        try {
            Verification verification = Verification.creator(
                            twilioConfig.getPathServiceSid(), // this is your verification sid
                            phoneNo, //this is your Twilio verified recipient phone number
                            "sms") // this is your channel type
                    .create();
            return verification.getStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }

    @Override
    public boolean checkOtpVerification(String phoneNo, String code) {
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
        try {
            VerificationCheck verificationCheck = VerificationCheck.creator(
                            twilioConfig.getPathServiceSid())
                    .setTo(phoneNo)
                    .setCode(code)
                    .create();
            if(verificationCheck.getStatus().equals("approved")) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public String getRandomOTP(String phoneNo) {
        String otp = new DecimalFormat("0000")
                .format(new Random().nextInt(9999));
        otpCache.put(phoneNo, otp);
        return otp;
    }

    @Override
    public void createTwilioOtpCache(String phoneNo, String code) {
        otpCache.put(phoneNo, code);
    }

    @Override
    public String getCacheOtp(String key) {
        try {
            return otpCache.get(key);
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public void clearOtp(String key) {
        otpCache.invalidate(key);
    }


}
