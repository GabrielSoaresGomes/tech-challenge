package com.postech.challenge_01.mappers;

import com.postech.challenge_01.dtos.requests.UserTypeRequestDTO;
import com.postech.challenge_01.dtos.responses.UserTypeResponseDTO;
import com.postech.challenge_01.domain.UserType;

import java.util.List;
import java.util.stream.Collectors;

public class UserTypeMapper {
    public static UserType userTypeRequestDTOToUserType(
            UserTypeRequestDTO dto
    ) {
        return userTypeRequestDTOToUserType(null, dto);
    }

    public static UserType userTypeRequestDTOToUserType(
            Long id,
            UserTypeRequestDTO dto
    ) {
        return new UserType(
                id,
                dto.name(),
                dto.type()
        );
    }

    public static UserTypeResponseDTO userTypeToUserTypeResponseDTO(UserType entity) {
        return new UserTypeResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getType()
        );
    }

    public static List<UserTypeResponseDTO> userTypeToUserTypeResponseDTOList(List<UserType> entities) {
        return entities.stream()
                .map(UserTypeMapper::userTypeToUserTypeResponseDTO)
                .collect(Collectors.toList());
    }
}
