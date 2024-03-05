package bluedragonvn.com.healmate.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: blued, Date: 3/5/2024
 */
@Data
public class AuthRequest implements Serializable {
    private String otp;
    private String phoneNo;
}
