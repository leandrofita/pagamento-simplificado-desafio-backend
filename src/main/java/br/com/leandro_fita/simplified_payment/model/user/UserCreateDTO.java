package br.com.leandro_fita.simplified_payment.model.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCreateDTO {

    @NotBlank
    private String fullName;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String cpf;
}
