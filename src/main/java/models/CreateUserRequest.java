package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import generators.GeneratingRule;
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
}

//создай классБ который на вход принимает data class с описанием поля и типа данны
// и генерирует сущность с рандомными значениями с требованиями чт если анатация generatingRule то используй
// для генерации ргэксп в её аргументе
