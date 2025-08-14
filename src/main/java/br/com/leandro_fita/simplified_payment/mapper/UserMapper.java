package br.com.leandro_fita.simplified_payment.mapper;

import br.com.leandro_fita.simplified_payment.domain.User;
import br.com.leandro_fita.simplified_payment.domain.Wallet;
import br.com.leandro_fita.simplified_payment.model.user.UserCreateDTO;
import br.com.leandro_fita.simplified_payment.model.user.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserCreateDTO dto);

    @Mapping(target = "walletOwnerId", source = "walletOwner.id")
    UserResponseDTO toResponseDTO(User user);
}
