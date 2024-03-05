package bluedragonvn.com.healmate;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;

public class DemoSMS {
    public static final String ACCOUNT_SID = "AC1407a88c6622a043ed3c049156c5bc0f";
    public static final String AUTH_TOKEN = "7c65b2bb8334c03e76b31df0a7b1815d";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Verification verification = Verification.creator(
                        "VAf433bd2a2c40c2acdffc1548235c11cb", // this is your verification sid
                        "+84367209442", //this is your Twilio verified recipient phone number
                        "sms") // this is your channel type
                .create();

        System.out.println(verification.getStatus());
    }
}
