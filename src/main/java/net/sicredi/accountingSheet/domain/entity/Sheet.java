package net.sicredi.accountingSheet.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opencsv.bean.CsvDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Objects;

@Document(collection = "sheets")
@JsonIgnoreProperties(value = {"createAt", "deletedAt"}, allowGetters = true)
public class Sheet extends AbstractEntity<String> {

    @NotBlank
    private String cooperative;

    @NotBlank
    private String agency;

    @NotBlank
    private String account;

    @NotBlank
    @CsvDate("dd/MM/yyyy")
    @Past
    private LocalDate date;

    @NotBlank
    private String description;

    @NotBlank
    private Double value;

    @NotBlank
    private String responsibility;

    private String status;

    private String emailDate;

    @NotBlank
    private String criticality;

    private String note;

    private String importedBy;

    public Sheet() {
    }

    public Sheet(@NotBlank String cooperative, @NotBlank String agency, @NotBlank String account,
                 @NotBlank @Past LocalDate date, @NotBlank String description, @NotBlank Double value,
                 @NotBlank String responsibility, String status, String emailDate, @NotBlank String criticality,
                 @NotBlank String importedBy) {
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
        this.importedBy = importedBy;
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
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

    public String getImportedBy() {
        return importedBy;
    }

    public void setImportedBy(String importedBy) {
        this.importedBy = importedBy;
    }

    @Override
    public LocalDate getCreatedAt() {
        return super.createdAt;
    }

    @Override
    public LocalDate getDeletedAt() {
        return super.deletedAt;
    }

    @Override
    public void setDeletedAt(LocalDate deletedAt) {
        this.deletedAt = deletedAt;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sheet sheet = (Sheet) o;
        return id.equals(sheet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Sheet{" +
                "id='" + id + '\'' +
                ", cooperative='" + cooperative + '\'' +
                ", agency='" + agency + '\'' +
                ", account='" + account + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", value=" + value +
                ", responsibility='" + responsibility + '\'' +
                ", status='" + status + '\'' +
                ", emailDate='" + emailDate + '\'' +
                ", criticality='" + criticality + '\'' +
                ", note='" + note + '\'' +
                ", createdAt=" + createdAt +
                ", deletedAt=" + deletedAt +
                '}';
    }
}
