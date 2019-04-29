package net.sicredi.accountingSheet.controllers;

import net.sicredi.accountingSheet.domain.dto.AccountDTO;
import net.sicredi.accountingSheet.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api")
public class AccountController extends AbstractController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/account/{number}", method = RequestMethod.GET)
    public ResponseEntity getAccountAndTotal(@PathVariable(value="number") String number){
        Collection<AccountDTO> getAccountTotal = accountService.findByAccountStartingWith(number);
        return ResponseEntity.ok(getAccountTotal);
    }

    @PostMapping(value = "/account")
    public ResponseEntity createAccount(@Valid @RequestBody AccountDTO accountDTO) throws Throwable{
        return buildResponse(accountService.createAccount(accountDTO));
    }
}
