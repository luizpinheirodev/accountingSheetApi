package net.sicredi.accountingSheet.service;

import net.sicredi.accountingSheet.domain.FileStatus;
import net.sicredi.accountingSheet.domain.dto.FileImportDTO;
import net.sicredi.accountingSheet.domain.dto.SheetDTO;
import net.sicredi.accountingSheet.domain.entity.Coop;
import net.sicredi.accountingSheet.domain.entity.FileImport;
import net.sicredi.accountingSheet.domain.entity.Sheet;
import net.sicredi.accountingSheet.mapper.FileImportMapper;
import net.sicredi.accountingSheet.mapper.SheetMapper;
import net.sicredi.accountingSheet.repositories.CoopRepository;
import net.sicredi.accountingSheet.repositories.FileImportRepository;
import net.sicredi.accountingSheet.repositories.SheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class FileImportServiceImpl implements FileImportService {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final FileImportRepository fileImportRepository;
    private final SheetRepository sheetRepository;
    private final CoopRepository coopRepository;
    private final FileImportMapper fileImportMapper;
    private final SheetMapper sheetMapper;

    @Autowired
    public FileImportServiceImpl(FileImportRepository fileImportRepository, SheetRepository sheetRepository,
                                 CoopRepository coopRepository, FileImportMapper fileImportMapper, SheetMapper sheetMapper) {
        this.fileImportRepository = fileImportRepository;
        this.sheetRepository = sheetRepository;
        this.coopRepository = coopRepository;
        this.fileImportMapper = fileImportMapper;
        this.sheetMapper = sheetMapper;
    }

    @Override
    public FileImport storeFile(String uploadFileName) {
        FileImport fileImport = fileImportRepository.save(new FileImport(uploadFileName));
        return fileImport;
    }

    @Override
    public FileImportDTO saveFileData(MultipartFile file, FileImport fileImport) throws IOException {

        long startSecond = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

        fileImport = parseUploadedCSVtoDTO(file, fileImport);
        FileImportDTO fileImportDTO = new FileImportDTO();
        fileImportDTO = fileImportMapper.toDTO(fileImport);

        long endSecond = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        fileImport.setFileType(file.getContentType());
        fileImport.setSize(file.getSize());
        fileImport.setProcessingTime(endSecond - startSecond);

        fileImportRepository.save(fileImport);
        return fileImportDTO;
    }

    @Override
    public Optional<Collection<FileImportDTO>> findAll() {
        return Optional.ofNullable(fileImportRepository.findAll()).flatMap(fileImportMapper::toDTO);
    }

    @Override
    public Optional<Collection<SheetDTO>> saveFileDataReturnList(MultipartFile file, FileImport fileImport) throws IOException {

        Collection<Sheet> finalList = parseUploadedCSVtoList(file, fileImport);

        Optional<Collection<SheetDTO>> sheetDTOs = sheetMapper.toDTO(finalList);


        fileImport.setFileType(file.getContentType());
        fileImport.setSize(file.getSize());
        fileImportRepository.save(fileImport);
        return sheetDTOs;
    }

    private Collection<Sheet> parseUploadedCSVtoList(MultipartFile file, FileImport fileImport) throws IOException {
        long startSecond = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

        List<Coop> listCoop = coopRepository.findAll();

        Collection<Sheet> sheetList = new ArrayList<>();
        List<Sheet> sheetListError = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(file.getInputStream()));) {
            Pattern p = Pattern.compile(";(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))");

            List<Sheet> uploadedSheetList = in.lines().skip(1).map(tmpLine -> {
                Sheet sheet = new Sheet();

                int columnIdx = 0;
                for (String tmpData : p.split(tmpLine)) {
                    if (columnIdx <= 10) {
                        switch (columnIdx) {
                            case 0:
                                sheet.setCooperative(tmpData);
                                break;
                            case 1:
                                sheet.setAgency(tmpData);
                                break;
                            case 2:
                                sheet.setAccount(tmpData);
                                break;
                            case 3:
                                sheet.setDate(LocalDate.parse(tmpData, formatter));
                                break;
                            case 4:
                                sheet.setDescription(tmpData);
                                break;
                            case 5:
                                sheet.setValue(Double.parseDouble(tmpData.replace(".", "").replace(",", ".")));
                                break;
                            case 6:
                                sheet.setResponsibility(tmpData);
                                break;
                            case 7:
                                sheet.setStatus(tmpData);
                                break;
                            case 8:
                                sheet.setEmailDate(tmpData);
                                break;
                            case 9:
                                sheet.setCriticality(tmpData);
                                break;
                            default:
                                break;
                        }
                        columnIdx++;
                    } else {
                        columnIdx = 0;
                    }
                }

                sheet.setImportedBy(fileImport.id);

                Coop coopExist = listCoop.stream().filter(x -> x.getNumber().matches(sheet.getCooperative())).findFirst().orElse(null);

                if (coopExist != null
                        && sheet.getAgency() != null
                        && sheet.getAgency() != "00"
                        && sheet.getAgency().length() <= 2
                        && sheet.getAccount().length() == 10
                        && sheet.getDate() != null
                        && sheet.getDate().isBefore(LocalDate.now())
                        && sheet.getValue() != null
                        && sheet.getDescription() != null) {
                    sheetList.add(sheet);
                } else {
                    sheetListError.add(sheet);
                }

                coopExist = null;
                return sheet;

            }).collect(Collectors.toList());


            if (sheetListError.isEmpty()) {
                sheetRepository.saveAll(sheetList);
                fileImport.setStatus(FileStatus.PROCESSED);
                long endSecond = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
                fileImport.setProcessingTime(endSecond - startSecond);
                return sheetList;
            } else {
                fileImport.setStatus(FileStatus.ERROR);
                long endSecond = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
                fileImport.setProcessingTime(endSecond - startSecond);
                return sheetListError;
            }
        }
    }


    public FileImport parseUploadedCSVtoDTO(MultipartFile file, FileImport fileImport) throws IOException {

        /*

        //collect all cooperatives to check validation
        List<Coop> listCoop = coopRepository.findAll();

        List<Sheet> sheetList = new ArrayList<>();
        List<Sheet> sheetListError = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(file.getInputStream()));) {
            Pattern p = Pattern.compile(";(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))");

            List<Sheet> uploadedSheetList = in.lines().skip(1).map(tmpLine -> {
                Sheet sheet = new Sheet();

                int columnIdx = 0;
                for (String tmpData : p.split(tmpLine)) {
                    if (columnIdx <= 10) {
                        switch (columnIdx) {
                            case 0:
                                sheet.setCooperative(tmpData);
                                break;
                            case 1:
                                sheet.setAgency(tmpData);
                                break;
                            case 2:
                                sheet.setNumber(tmpData);
                                break;
                            case 3:
                                sheet.setDate(LocalDate.parse(tmpData, formatter));
                                break;
                            case 4:
                                sheet.setDescription(tmpData);
                                break;
                            case 5:
                                sheet.setValue(new BigDecimal(tmpData.replace(".", "").replace(",", ".")));
                                break;
                            case 6:
                                sheet.setResponsibility(tmpData);
                                break;
                            case 7:
                                sheet.setStatus(tmpData);
                                break;
                            case 8:
                                sheet.setEmailDate(tmpData);
                                break;
                            case 9:
                                sheet.setCriticality(tmpData);
                                break;
                            default:
                                break;
                        }
                        columnIdx++;
                    } else {
                        columnIdx = 0;
                    }
                }

                sheet.setImportedBy(fileImport.id);

                Coop coopExist = listCoop.stream().filter(x -> x.getNumber().matches(sheet.getCooperative())).findFirst().orElse(null);

                if(coopExist != null
                        && sheet.getAgency() != null
                        && sheet.getAgency() != "00"
                        && sheet.getAgency().length() <= 2
                        && sheet.getNumber().length() == 10
                        && sheet.getDate() != null
                        && sheet.getDate().isBefore(LocalDate.now())
                        && sheet.getValue() != null
                        && sheet.getDescription() != null){
                    sheetList.add(sheet);
                }else{
                    sheetListError.add(sheet);
                }

                coopExist = null;
                return sheet;

            }).collect(Collectors.toList());


            if(sheetListError.isEmpty()){
                sheetRepository.saveAll(sheetList);
                fileImport.setStatus(FileStatus.PROCESSED);
            }else{
                fileImport.setStatus(FileStatus.ERROR);
            }
        }
        return fileImport;
    }





    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // Check if the file's name contains invalid characters
        if (fileName.contains("..")) {
            throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
        }
        String fileDownloadUri = file.getName();
        String fileType = file.getContentType();
        long size = file.getSize();
        FileStatus status = FileStatus.SENT;
        FileImport importFile = new FileImport(fileName, fileDownloadUri, fileType, size, status);


        //ImportCsv iC = new ImportCsv(file);
        //List<String[]> allData = iC.readAll();

        List<Sheet> sheetList = new ArrayList<>();

        return fileName;



        */
        return null;
    }


}
