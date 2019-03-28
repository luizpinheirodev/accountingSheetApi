package net.sicredi.accountingSheet.domain.entity;

import net.sicredi.accountingSheet.domain.FileStatus;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "files")
public class FileImport extends AbstractEntity <String> {

    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private FileStatus status;
    private Long processingTime;

    public FileImport(String fileName) {
        this.fileName = fileName;
        this.status = FileStatus.SENT;
        super.createdAt = LocalDate.now();
    }

    public FileImport(String fileName, String fileDownloadUri, String fileType, long size, FileStatus status) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        this.status = status;
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

    public Long getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(Long processingTime) {
        this.processingTime = processingTime;
    }

    public void setStatus(FileStatus status) {
        this.status = status;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public LocalDate getCreatedAt() {
        return null;
    }

    @Override
    public LocalDate getDeletedAt() {
        return null;
    }

    @Override
    void setDeletedAt(LocalDate deletedAt) {

    }
}
