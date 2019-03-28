package net.sicredi.accountingSheet.service;

import net.sicredi.accountingSheet.domain.dto.FileImportDTO;
import net.sicredi.accountingSheet.domain.entity.FileImport;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public interface FileImportService {

    FileImport storeFile(String uploadFileName);

    FileImportDTO saveFileData(MultipartFile file, FileImport fileImport) throws IOException;

    Optional<Collection<FileImportDTO>> findAll();

    //String storeFile(MultipartFile file);

}
