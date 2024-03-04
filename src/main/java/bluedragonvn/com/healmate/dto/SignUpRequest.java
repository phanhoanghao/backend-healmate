package bluedragonvn.com.healmate.dto;

import bluedragonvn.com.healmate.service.ValidPhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

  private String userId;

  @NotNull
  @NotBlank(message="Please enter your phone number")
  @Size(max = 15, message = "Phone length should not be grater than 15 characters")
  @ValidPhoneNumber(message="Please enter a valid phone number")
  private String phone;
}