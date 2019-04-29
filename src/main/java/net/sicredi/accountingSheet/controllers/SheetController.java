package net.sicredi.accountingSheet.controllers;

import net.sicredi.accountingSheet.domain.dto.SheetDTO;
import net.sicredi.accountingSheet.service.SheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
//@CrossOrigin("*")
public class SheetController {

    private final SheetService sheetService;

    @Autowired
    public SheetController(SheetService sheetService) {
        this.sheetService = sheetService;
    }

    @RequestMapping(value = "/sheets", method = RequestMethod.GET)
    public ResponseEntity<Collection<SheetDTO>> getAll(){
        Optional<Collection<SheetDTO>> listDTO = sheetService.findAll();
        if(listDTO.isPresent()){
            return ResponseEntity.ok(listDTO.get());
        }
        return ResponseEntity.ok(null);
    }







}
