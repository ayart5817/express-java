package api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferResponse extends BaseModel {
    private String message;
    private String status;
    private Double amount;
    private Double fraudRiskScore;
    private String fraudReason;
    private Long senderAccountId;
    private Long receiverAccountId;
    private boolean requiresManualReview;
    private boolean requiresVerification;

}