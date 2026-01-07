package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest extends BaseModel {
    private int senderAccountId;
    private int receiverAccountId;
    private Double amount;

}
