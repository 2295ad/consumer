package com.skylo.consumer.persistence.entity.mapper;

import com.skylo.consumer.dto.TransactionDTO;
import com.skylo.consumer.persistence.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionEntityMapper extends EntityMapper<TransactionDTO, TransactionEntity> {}
