package ai.moneymate.api.accounts.controller;

import ai.moneymate.api.accounts.dto.AccountDTO;
import ai.moneymate.api.accounts.service.AccountsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/accounts")
@RequiredArgsConstructor
@Log4j2
public class AccountsController {
    private final AccountsService accountsService;

    @GetMapping("{id}")
    public ResponseEntity<AccountDTO> getAccountByID(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.accountsService.getAccountInfoByID(id));
    }

    @GetMapping
    public ResponseEntity<Set<AccountDTO>> getAuthenticatedUserAccount() {
        return ResponseEntity.status(HttpStatus.FOUND).body(this.accountsService.getAuthenticatedUserAccount());
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody AccountRequest accountRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.accountsService.createAccountForAuthenticatedUser(accountRequest));
    }
}
