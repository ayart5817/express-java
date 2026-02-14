package api.models;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositRequest extends BaseModel {
    private long id;
    private double balance;

}
