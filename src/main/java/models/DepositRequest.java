package models;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositRequest extends BaseModel{
    private int id;
    private double balance;

}
