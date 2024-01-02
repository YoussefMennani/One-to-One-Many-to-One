package com.example.jeeioa.controller;

import com.example.jeeioa.entities.AccountUM5R;
import com.example.jeeioa.entities.Student;
import com.example.jeeioa.repositories.AccountUM5RRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts/")
public class AccountUM5RController {

    @Autowired
    private AccountUM5RRepository accountUM5RRepository;

    @GetMapping
    public ResponseEntity<List<AccountUM5R>> getAllAccountUM5R() {
        List<AccountUM5R> accountUM5RS = accountUM5RRepository.findAll();
        if (accountUM5RS.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(accountUM5RS);
        }
    }


    @GetMapping("{id}")
    public ResponseEntity<AccountUM5R> getAccountUM5RById(@PathVariable Long id) {
        Optional<AccountUM5R> accountUM5ROptional = accountUM5RRepository.findById(id);
        return accountUM5ROptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AccountUM5R> createAccountUM5R(@RequestBody AccountUM5R accountUM5R) {
        AccountUM5R savedAccountUM5R = accountUM5RRepository.save(accountUM5R);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccountUM5R);
    }

    @PutMapping("{id}")
    public ResponseEntity<AccountUM5R> updateAccountUM5R(@PathVariable Long id, @RequestBody AccountUM5R updatedAccountUM5R) {
        Optional<AccountUM5R> existingAccountUM5ROptional = accountUM5RRepository.findById(id);

        if (existingAccountUM5ROptional.isPresent()) {
            updatedAccountUM5R.setId(id);
            AccountUM5R savedAccountUM5R = accountUM5RRepository.save(updatedAccountUM5R);
            return ResponseEntity.ok(savedAccountUM5R);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAccountUM5R(@PathVariable Long id) {
        Optional<AccountUM5R> accountUM5ROptional = accountUM5RRepository.findById(id);

        if (accountUM5ROptional.isPresent()) {
            accountUM5RRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

