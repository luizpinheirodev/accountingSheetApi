package net.sicredi.accountingSheet.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Document(collection = "coops")
@JsonIgnoreProperties(value = {"createAt", "deletedAt"}, allowGetters = true)
public class Coop extends AbstractEntity<String> {

    @NotBlank
    @Indexed
    @UniqueElements
    private String number;

    @NotBlank
    private String name;

    public Coop() {
    }

    public Coop(@NotBlank String number, @NotBlank String name) {
        this.number = number;
        this.name = name;
        createdAt = LocalDate.now();
    }

    @NotNull
    @Override
    public String getId() {
        return super.id;
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
        Coop coop = (Coop) o;
        return number.equals(coop.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "Coop{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", deletedAt=" + deletedAt +
                '}';
    }

}
