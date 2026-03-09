package api.models;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositRequest extends BaseModel {
    private Long accountId;
    private double amount;
    private String description;
}
