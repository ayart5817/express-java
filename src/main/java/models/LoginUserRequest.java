package models;

import lombok.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginUserRequest extends BaseModel {
    private String username;
    private String password;
}
