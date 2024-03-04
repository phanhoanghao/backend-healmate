package bluedragonvn.com.healmate.dto;

import bluedragonvn.com.healmate.service.ValidPhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: phanh, Date : 3/5/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequestFromAdmin {

    @NotBlank
    @NotNull
    @Size(max = 15, message = "Phone length should not be grater than 15 characters")
    @ValidPhoneNumber(message = "Please enter a valid phone number")
    private String phone;

    private List<String> roles = new ArrayList<>();


}
