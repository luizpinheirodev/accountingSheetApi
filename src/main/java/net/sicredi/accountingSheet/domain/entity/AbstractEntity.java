package net.sicredi.accountingSheet.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class AbstractEntity<ID extends Serializable & Comparable<ID>> implements Serializable {

    private static final long serialVersionUID = -2820768155445338200L;

    public ID id;
    public LocalDate createdAt;
    public LocalDate deletedAt;

    abstract public ID getId();
    abstract public LocalDate getCreatedAt();
    abstract public LocalDate getDeletedAt();
    abstract void setDeletedAt(LocalDate deletedAt);

    @Override
    public boolean equals(Object obj) {
        throw new UnsupportedOperationException("equals() for " + this.getClass().getName()
                + " not implemented, need business key");
    }

    @Override
    public int hashCode()
    {
        throw new UnsupportedOperationException( "hashCode() for " + this.getClass().getName()
                + " not implemented, need business key" );
    }

    @Override
    public String toString()
    {
        return this.getClass().getName() + "[id=" + id + "]";
    }

}
