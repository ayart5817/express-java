package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginUserRequest extends BaseModel {
    private String username;
    private String password;
}
