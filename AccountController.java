package com.javaguides.banking_app.controller;

import com.javaguides.banking_app.dto.AccountDto;
import com.javaguides.banking_app.entity.Account;
import com.javaguides.banking_app.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //Add Account RestApi

    @PostMapping
    public ResponseEntity<AccountDto> addAccount (@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    //Get Account RespApi
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto= accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    //Deposit ResTApi

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,@RequestBody Map<String,Double> request){
        AccountDto accountDto=accountService.deposit(id,request.get("amount"));
        return ResponseEntity.ok(accountDto);
    }

    //Withdraw RestApi

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw (@PathVariable Long id,@RequestBody Map<String,Double> request){
        AccountDto accountDto=accountService.withdraw(id,request.get("amount"));
        return ResponseEntity.ok(accountDto);


    }

    //Get all Accounts Restapi

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
       List<AccountDto> accounts= accountService.getAllAccounts();
       return ResponseEntity.ok(accounts);
    }

    //Delete AccountRestApi

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is Deleted Successfully!");
    }
}
