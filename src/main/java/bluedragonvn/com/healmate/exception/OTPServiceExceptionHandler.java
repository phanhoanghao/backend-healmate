package bluedragonvn.com.healmate.exception;

/**
 * OtpCache Exception
 */
public class OTPServiceExceptionHandler extends RuntimeException {

    /**
     * OtpCache Exception with error message
     *
     * @param errorMessage error message
     */
    public OTPServiceExceptionHandler(String errorMessage) {
        super(errorMessage);
    }

    /**
     * OtpCache Exception with error message and throwable
     *
     * @param errorMessage error message
     * @param throwable    error
     */
    public OTPServiceExceptionHandler(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }

}