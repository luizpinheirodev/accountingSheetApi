package net.sicredi.accountingSheet.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Objects;

@Document(collection = "sheets")
@JsonIgnoreProperties(value = {"createAt"}, allowGetters = true)
public class Sheet {

    @Id
    private String id;

    @NotBlank
    private String account;

    @NotBlank
    private String cooperative;

    @NotBlank
    private String agency;

    @NotBlank
    private Date date;

    @NotBlank
    private String description;

    @NotBlank
    private String responsibility;

    private String criticality;

    private String note;

    @NotBlank
    private Date createdAt = new Date();

    private Date deletedAt;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createAt) {
        this.createdAt = createAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
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
                ", account='" + account + '\'' +
                ", cooperative='" + cooperative + '\'' +
                ", agency='" + agency + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", responsibility='" + responsibility + '\'' +
                ", criticality='" + criticality + '\'' +
                ", note='" + note + '\'' +
                ", createdAt=" + createdAt +
                ", deletedAt=" + deletedAt +
                '}';
    }
}
