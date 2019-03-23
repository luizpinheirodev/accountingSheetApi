package net.sicredi.accountingSheet.domain.dto;

import net.sicredi.accountingSheet.domain.entity.Coop;

import java.time.LocalDate;

public class CoopDTO extends AbstractDTO {

    private String id;
    private String number;
    private String name;
    private LocalDate createdAt;
    private LocalDate deletedAt;

    public CoopDTO() {
    }

    public CoopDTO(Coop coop) {
        number = coop.getNumber();
        name = coop.getName();
        createdAt = LocalDate.now();
    }

    public String getId() {
        return id;
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
