package com.acme.esb.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.acme.esb.dto.MessagePayloadDTO;
import com.acme.esb.dto.OutputPayloadDTO;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface PayloadMapper {

    @Mapping(target = "id", source = "transactionId")
    public OutputPayloadDTO toOuputPayload(MessagePayloadDTO request);

    public List<OutputPayloadDTO> tOutputPayloadDTOs(List<MessagePayloadDTO> requests);
}
