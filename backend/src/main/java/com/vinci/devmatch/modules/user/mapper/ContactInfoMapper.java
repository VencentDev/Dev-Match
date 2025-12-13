package com.vinci.devmatch.modules.user.mapper;

import com.vinci.devmatch.modules.user.dto.ContactInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContactInfoMapper {

    ContactInfoMapper INSTANCE = Mappers.getMapper(ContactInfoMapper.class);

    ContactInfo toDto(ContactInfo entity);

    ContactInfo toEntity(ContactInfo dto);
}
