package ai.moneymate.api.accounts.service;

import ai.moneymate.api.accounts.controller.AccountRequest;
import ai.moneymate.api.accounts.dto.AccountDTO;
import ai.moneymate.api.accounts.entities.AccountsEntity;
import ai.moneymate.api.accounts.repository.AccountsRepository;
import ai.moneymate.api.users.entities.UserEntity;
import ai.moneymate.api.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountsService {
    private final AccountsRepository accountsRepository;
    private final UserRepository userRepository;

    public AccountDTO getAccountInfoByID(int id) {
        Optional<AccountsEntity> accountEntity = this.accountsRepository.findById(id);
        if (accountEntity.isEmpty())
            throw new RuntimeException();

        return AccountDTO.from(accountEntity.get());
    }

    public AccountDTO createAccountForAuthenticatedUser(AccountRequest accountRequest) {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = user.getId();

        AccountsEntity accountsEntity = accountRequest.asEntity();

        accountsEntity.setUser(this.userRepository.findById(userId).get());

        return AccountDTO.from(this.accountsRepository.save(accountsEntity));
    }

    public Set<AccountDTO> getAuthenticatedUserAccount() {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Set<AccountsEntity>> accounts = this.accountsRepository.findByUser(user);
        if (accounts.isEmpty())
            throw new RuntimeException();
        Set<AccountDTO> accountDTOSet = new HashSet<>();
        for (AccountsEntity acc : accounts.get()) {
            accountDTOSet.add(AccountDTO.from(acc));
        }
        return  accountDTOSet;
    }
}
