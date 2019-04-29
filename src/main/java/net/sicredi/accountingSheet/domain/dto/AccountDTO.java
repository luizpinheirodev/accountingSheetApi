package net.sicredi.accountingSheet.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class AccountDTO extends AbstractDTO {


    @Field("account")
    private String number;
    private String name;
    private Double total;

    public AccountDTO() {
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
