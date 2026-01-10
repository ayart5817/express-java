package models;

import lombok.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AccountResponse extends BaseModel {

    private long id;
    private String accountNumber;
    private double balance;
    private String username;
    private List<Transaction> transactions;


    // Метод для получения последней транзакции (самой новой)
    public Optional<Transaction> getLastTransaction() {
        if (transactions == null || transactions.isEmpty()) {
            return Optional.empty();
        }
        return transactions.stream()
                .max(Comparator.comparing(Transaction::getId));
    }

    // Метод для получения всех транзакций отсортированных по ID (новые сначала)
    public List<Transaction> getSortedTransactions() {
        if (transactions == null) {
            return List.of();
        }
        return transactions.stream()
                .sorted(Comparator.comparing(Transaction::getId).reversed())
                .collect(Collectors.toList());
    }


    //была ошибка проект, не компилировался без строк

    public long getId() {
        return id;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}

