package com.vinci.devmatch.modules.user.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactInfoMapper {

    com.vinci.devmatch.modules.user.dto.ContactInfo toDto(
            com.vinci.devmatch.modules.user.entity.ContactInfo entity
    );

    com.vinci.devmatch.modules.user.entity.ContactInfo toEntity(
            com.vinci.devmatch.modules.user.dto.ContactInfo dto
    );
}