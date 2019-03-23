package net.sicredi.accountingSheet.service;

import net.sicredi.accountingSheet.domain.dto.SheetDTO;
import net.sicredi.accountingSheet.mapper.SheetMapper;
import net.sicredi.accountingSheet.repositories.SheetRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class SheetServiceImpl implements SheetService {

    private final SheetRepository sheetRepository;
    private final SheetMapper sheetMapper;

    public SheetServiceImpl(SheetRepository sheetRepository, SheetMapper sheetMapper) {
        this.sheetRepository = sheetRepository;
        this.sheetMapper = sheetMapper;
    }

    @Override
    public Optional<Collection<SheetDTO>> findAll() {
        return Optional.ofNullable(sheetRepository.findAll()).flatMap(sheetMapper::toDTO);
    }
}
