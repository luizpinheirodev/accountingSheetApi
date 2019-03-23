package net.sicredi.accountingSheet.controllers;

import net.sicredi.accountingSheet.domain.dto.CoopDTO;
import net.sicredi.accountingSheet.service.CoopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
//@CrossOrigin("*")
public class CoopController {

    private final CoopService coopService;

    @Autowired
    public CoopController(CoopService coopService) {
        this.coopService = coopService;
    }

    @RequestMapping(value = "/coops", method = RequestMethod.GET)
    public ResponseEntity<Collection<CoopDTO>> getAll(){
        Optional<Collection<CoopDTO>> listDTO = coopService.findAll();
        if(listDTO.isPresent()){
            return ResponseEntity.ok(listDTO.get());
        }
        return ResponseEntity.ok(null);
    }

}
