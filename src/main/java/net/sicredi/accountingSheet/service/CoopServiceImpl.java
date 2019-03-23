package net.sicredi.accountingSheet.service;

import net.sicredi.accountingSheet.domain.dto.CoopDTO;
import net.sicredi.accountingSheet.mapper.CoopMapper;
import net.sicredi.accountingSheet.repositories.CoopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CoopServiceImpl implements CoopService {

    private final CoopRepository coopRepository;
    private final CoopMapper coopMapper;

    @Autowired
    public CoopServiceImpl(CoopRepository coopRepository, CoopMapper coopMapper) {
        this.coopRepository = coopRepository;
        this.coopMapper = coopMapper;
    }

    //@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Optional<Collection<CoopDTO>> findAll() {
        return Optional.ofNullable(coopRepository.findAll()).flatMap(coopMapper::toDTO);
    }

    @Override
    public CoopDTO findOne() {
        return null;
    }

    @Override
    public CoopDTO createCoop() {
        return null;
    }



}
