package net.sicredi.accountingSheet.controllers;

import net.sicredi.accountingSheet.entities.Sheet;
import net.sicredi.accountingSheet.repositories.SheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class SheetController {

    @Autowired
    private SheetRepository sheetRepository;

    @GetMapping("/sheets")
    public List<Sheet> getAllSheets(){
        Sort sortByCreateAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return sheetRepository.findAll(sortByCreateAtDesc);
    }

    public Sheet createSheet(@Valid @RequestBody Sheet sheet){
        return sheetRepository.save(sheet);
    }
}
