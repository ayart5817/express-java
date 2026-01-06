package models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransferResponse extends BaseModel {
    private String message;
    private Double amount;
    private Long senderAccountId;
    private Long receiverAccountId;
}