package bluedragonvn.com.healmate.exception;

import lombok.Getter;

/**
 * @author: phanh, Date : 3/5/2024
 */
public class SuccessCodeWithErrorResponse extends RuntimeException {

    @Getter
    private ErrorResponse errorResponse;

    @Getter
    private String id;

    public SuccessCodeWithErrorResponse(String id, ErrorResponse errorResponse) {
        this.id = id;
        this.errorResponse = errorResponse;
    }

    public SuccessCodeWithErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

}
