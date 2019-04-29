package net.sicredi.accountingSheet.controllers;

import net.sicredi.accountingSheet.domain.FileStatus;
import net.sicredi.accountingSheet.domain.dto.FileImportDTO;
import net.sicredi.accountingSheet.domain.dto.SheetDTO;
import net.sicredi.accountingSheet.domain.entity.FileImport;
import net.sicredi.accountingSheet.service.FileImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FileController {

    private FileImportService fileImportService;

    @Autowired
    public FileController(FileImportService importFileService) {
        this.fileImportService = importFileService;
    }

    @RequestMapping(value = "/file", method = RequestMethod.GET)
    public ResponseEntity<Collection<FileImportDTO>> getAll(){
        Optional<Collection<FileImportDTO>> listDTO = fileImportService.findAll();
        if(listDTO.isPresent()){
            return ResponseEntity.ok(listDTO.get());
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("/file")
    public ResponseEntity<Object> handleFileUpload(@Valid @RequestBody MultipartFile file) throws IOException {
        FileImport fileImportObj = fileImportService.storeFile(file.getOriginalFilename());
        Optional<Collection<SheetDTO>> finalList = fileImportService.saveFileDataReturnList(file, fileImportObj);

        if(fileImportObj.getStatus() == FileStatus.PROCESSED){
            return new ResponseEntity<>(fileImportObj, HttpStatus.OK);
        }

        return new ResponseEntity<>(finalList, HttpStatus.OK);
    }

}
