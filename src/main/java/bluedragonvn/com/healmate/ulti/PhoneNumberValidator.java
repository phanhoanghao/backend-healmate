package bluedragonvn.com.healmate.ulti;

import bluedragonvn.com.healmate.config.TwilioConfig;
import bluedragonvn.com.healmate.service.ValidPhoneNumber;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.lookups.v1.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Configurable
public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    @Autowired
    private TwilioConfig twilioConfig;

   @Override
   public void initialize(ValidPhoneNumber constraintAnnotation) {
       Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
   }

   @Override
   public boolean isValid(String value, ConstraintValidatorContext context) {

       // The Lookup API requires your phone number in E.164 format
       // E.164 formatted phone numbers must not have spaces in them
       value = value.replaceAll("[\\s()-]", "");

       if ("".equals(value)){
           return false;
       }

       try {
           PhoneNumber.fetcher(new com.twilio.type.PhoneNumber(value)).fetch();
           return true;

       } catch (ApiException e){
           // The Lookup API returns HTTP 404 if the phone number is not found
           // (ie it is not a real phone number)
           if (e.getStatusCode() == 404){
               return false;
           }
           throw e;
       }
   }
}