package br.com.leandro_fita.simplified_payment.model.user;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UserResponseDTO {

    private Long id;
    private String fullName;
    private String email;
    private String cpf;
    private OffsetDateTime createdAt;
    private Long walletId;
    private Long walletOwnerId;

}
