package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
    private double amount;
    private String type; // "DEPOSIT", "TRANSFER"
    private int relatedAccountId;
    private Long id;


    public Long getId() {
        return id;
    }
}