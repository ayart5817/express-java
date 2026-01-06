package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileResponse{
    private Long id;
    private String username;
    private String name;
    private String role;
    private String password;
    private List<AccountResponse> accounts;

    // Метод для получения всех транзакций из всех аккаунтов, отсортированных
    public List<Transaction> getAllSortedTransactions() {
        if (accounts == null) {
            return Collections.emptyList();
        }

        return accounts.stream()
                .flatMap(account -> {
                    if (account.getTransactions() != null) {
                        return account.getTransactions().stream();
                    }
                    return java.util.stream.Stream.empty();
                })
                .sorted(Comparator.comparing(Transaction::getId).reversed()) // Новые сначала
                .collect(Collectors.toList());
    }
    // Метод для получения аккаунта (удобно для тестов)
    public Optional<AccountResponse> getAccountById(Integer accountId) {
        if (accounts == null) {
            return Optional.empty();
        }
        return accounts.stream()
                .filter(account -> account.getId() == accountId)
                .findFirst();
    }

}
