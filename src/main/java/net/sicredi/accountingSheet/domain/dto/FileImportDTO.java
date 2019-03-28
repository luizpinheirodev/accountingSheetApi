package net.sicredi.accountingSheet.domain.dto;

import net.sicredi.accountingSheet.domain.FileStatus;

public class FileImportDTO extends AbstractDTO {

    private String id;
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private FileStatus status;
    private Long processingTime;

    public FileImportDTO() {
    }

    public FileImportDTO(String fileName, String fileDownloadUri, String fileType, long size, FileStatus status, Long processingTime) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        this.status = status;
        this.processingTime = processingTime;
    }

    public FileImportDTO(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public FileStatus getStatus() {
        return status;
    }

    public void setStatus(FileStatus status) {
        this.status = status;
    }

    public Long getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(Long processingTime) {
        this.processingTime = processingTime;
    }
}
