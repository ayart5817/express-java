package api.models;

import api.configs.Config;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import api.generators.GeneratingRule;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequest extends BaseModel {
    @GeneratingRule(regex = "^[A-Z]{3}[a-z]{4}[0-9]{3}$")
    private String username;
    @GeneratingRule(regex = "^[A-Z]{3}[a-z]{4}[0-9]{3}[$%&]{2}$")
    private String password;
    @GeneratingRule(regex = "^USER$")
    private String role;

    public static CreateUserRequest getAdmin() {
        return CreateUserRequest.builder().username(Config.getProperty("admin.username"))
                .password(Config.getProperty("admin.password")).build();
    }
}

//создай классБ который на вход принимает data class с описанием поля и типа данны
// и генерирует сущность с рандомными значениями с требованиями чт если анатация generatingRule то используй
// для генерации ргэксп в её аргументе
