package net.sicredi.accountingSheet.service;

import io.vavr.control.Either;
import net.sicredi.accountingSheet.domain.dto.AccountDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public interface AccountService extends IService<AccountDTO, String> {

    Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    List<AccountDTO> findByAccountStartingWith (String number);
    Either<Throwable, AccountDTO> createAccount(AccountDTO dto);

}
