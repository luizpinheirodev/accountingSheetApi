package net.sicredi.accountingSheet.service;

import net.sicredi.accountingSheet.domain.dto.SheetDTO;

import java.util.Collection;
import java.util.Optional;

public interface SheetService extends IService<SheetDTO, String> {

    Optional<Collection<SheetDTO>> findAll();

    //List<AccountDTO> findAccountAndTotal(String number);

}
