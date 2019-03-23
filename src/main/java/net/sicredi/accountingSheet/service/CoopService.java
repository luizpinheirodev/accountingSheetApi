package net.sicredi.accountingSheet.service;

import net.sicredi.accountingSheet.domain.dto.CoopDTO;

import java.util.Collection;
import java.util.Optional;

public interface CoopService extends IService<CoopDTO, String> {

    Optional<Collection<CoopDTO>> findAll();
    //List<Coop> findAll();
    CoopDTO findOne();
    CoopDTO createCoop();

}
