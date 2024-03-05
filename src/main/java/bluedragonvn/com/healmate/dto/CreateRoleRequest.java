package bluedragonvn.com.healmate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author: phanh, Date : 3/5/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoleRequest{

    @NotBlank
    private String roleName;
    @NotBlank
    private String roleDescription;

}