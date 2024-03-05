package bluedragonvn.com.healmate.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: phanh, Date : 3/4/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Error {

    private String code;
    private String message;
}