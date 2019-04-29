package net.sicredi.accountingSheet.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Document(collection = "accounts")
@JsonIgnoreProperties(value = {"createAt", "deletedAt"}, allowGetters = true)
public class Account extends AbstractEntity<String> {

    @NotBlank
    @UniqueElements
    private String number;

    @NotBlank
    private String name;

    private String description;

    private Double total;

    public Account() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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
