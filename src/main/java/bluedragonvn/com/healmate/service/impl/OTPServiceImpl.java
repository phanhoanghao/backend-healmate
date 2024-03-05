package bluedragonvn.com.healmate.service.impl;

import bluedragonvn.com.healmate.config.TwilioConfig;
import bluedragonvn.com.healmate.service.OTPService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: blued, Date: 3/5/2024
 */
@Service
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
        PhoneNumber to = new PhoneNumber(phoneNo);
        PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
        String otp = getRandomOTP(phoneNo);
        String otpMessage = "Dear Customer , Your OTP is " + otp + ". Use this otp to log in to Rapido Clone Application";
        Message message = Message
                .creator(to, from,
                        otpMessage)
                .create();
        return  otp;
    }

    @Override
    public String getRandomOTP(String phoneNo) {
        String otp =  new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
        otpCache.put(phoneNo,otp);
        return otp;
    }

    @Override
    public String getCacheOtp(String key) {
        try{
            return otpCache.get(key);
        }catch (Exception e){
            return "";
        }
    }

    @Override
    public void clearOtp(String key) {
        otpCache.invalidate(key);
    }
}
