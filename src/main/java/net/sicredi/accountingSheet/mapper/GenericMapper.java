package net.sicredi.accountingSheet.mapper;

import net.sicredi.accountingSheet.domain.dto.AbstractDTO;
import net.sicredi.accountingSheet.domain.entity.AbstractEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract class GenericMapper<T extends AbstractDTO, H extends AbstractEntity> {

    @Autowired
    protected ModelMapper modelMapper;

    public T toDTO(H entity) {
        final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> dtoType = (Class<T>) (type).getActualTypeArguments()[0];

        final Optional<H> maybeEvent = Optional.ofNullable(entity);
        return maybeEvent
                .map(aClass -> modelMapper.map(entity, dtoType))
                .orElseThrow(() -> new IllegalArgumentException("Param \"entity\" can't be null!"));
    }

    public Optional<Collection<T>> toDTO(Collection<H> entities) {
        final Optional<Collection<H>> maybeEvents = Optional.ofNullable(entities);
        return maybeEvents.map(e -> {
            Stream<T> dtoStream = e.stream()
                    .map(this::toDTO);
            List<T> collect = dtoStream.collect(Collectors.toList());
            if (collect.isEmpty()) {
                return null;
            }
            return collect;
        });
    }

}
