package net.sicredi.accountingSheet.service;

import io.vavr.control.Either;
import io.vavr.control.Try;
import net.sicredi.accountingSheet.domain.dto.AccountDTO;
import net.sicredi.accountingSheet.domain.entity.Account;
import net.sicredi.accountingSheet.mapper.AccountMapper;
import net.sicredi.accountingSheet.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public List<AccountDTO> findByAccountStartingWith(String number) {
        return accountRepository.findByAccountStartingWith(number);
    }

    @Override
    public Either<Throwable, AccountDTO> createAccount(final AccountDTO accountDTO) {

        final Account account = accountMapper.toEntity(accountDTO);
        return Try.of(() -> {
            return accountMapper.toDTO(accountRepository.save(account));
        }).onSuccess(a -> LOGGER.info("OK"))
                .onFailure(e -> LOGGER.error("Error"))
                .toEither();
    }


}

