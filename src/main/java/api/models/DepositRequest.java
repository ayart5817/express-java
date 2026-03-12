package api.models;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositRequest extends BaseModel {
    private Long id;
    private double balance;
    //private String description;
}
