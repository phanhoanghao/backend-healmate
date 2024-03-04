package bluedragonvn.com.healmate.dto;

import bluedragonvn.com.healmate.repository.dao.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author: phanh, Date : 3/5/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUserResponse {
    private String userId;
    private String phone;
    private Set<Role> roles;
}
