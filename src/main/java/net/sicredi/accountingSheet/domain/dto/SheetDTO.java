package net.sicredi.accountingSheet.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SheetDTO extends AbstractDTO {

    private String id;
    private String cooperative;
    private String agency;
    private String account;
    private LocalDate date;
    private String description;
    private BigDecimal value;
    private String responsibility;
    private String status;
    private String emailDate;
    private String criticality;
    private String note;
    private LocalDate createdAt;
    private LocalDate deletedAt;

    public SheetDTO() {
    }

    public SheetDTO(String cooperative, String agency, String account, LocalDate date, String description,
                    BigDecimal value, String responsibility, String status, String emailDate, String criticality) {
        this.cooperative = cooperative;
        this.agency = agency;
        this.account = account;
        this.date = date;
        this.description = description;
        this.value = value;
        this.responsibility = responsibility;
        this.status = status;
        this.emailDate = emailDate;
        this.criticality = criticality;
        createdAt = LocalDate.now();
    }

    public String getId() {
        return id;
    }

    public String getCooperative() {
        return cooperative;
    }

    public void setCooperative(String cooperative) {
        this.cooperative = cooperative;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmailDate() {
        return emailDate;
    }

    public void setEmailDate(String emailDate) {
        this.emailDate = emailDate;
    }

    public String getCriticality() {
        return criticality;
    }

    public void setCriticality(String criticality) {
        this.criticality = criticality;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDate deletedAt) {
        this.deletedAt = deletedAt;
    }


}
