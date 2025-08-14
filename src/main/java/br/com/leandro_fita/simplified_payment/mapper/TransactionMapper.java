package br.com.leandro_fita.simplified_payment.mapper;

import br.com.leandro_fita.simplified_payment.domain.Transaction;
import br.com.leandro_fita.simplified_payment.model.transaction.TransactionCreateDTO;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    Transaction toEntity(TransactionCreateDTO dto);
}
