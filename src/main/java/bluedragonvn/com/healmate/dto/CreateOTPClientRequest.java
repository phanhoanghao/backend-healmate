package bluedragonvn.com.healmate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: phanh, Date : 3/5/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOTPClientRequest {
    private String phone;
    private String otp;
}
