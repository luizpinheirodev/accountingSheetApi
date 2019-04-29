package net.sicredi.accountingSheet.repositories.custom;

import net.sicredi.accountingSheet.domain.dto.AccountDTO;

import java.util.List;

public interface AccountRepositoryCustom {

    List<AccountDTO> findByAccountStartingWith (String number);

}
