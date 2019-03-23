package net.sicredi.accountingSheet.mapper;

import java.util.Collection;

public interface AbstractMapper<D, E> {
    D toDTO(E entity);
    Collection<D> entitiesToDTOs(Collection<E> entities);
}
