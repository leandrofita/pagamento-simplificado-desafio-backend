package br.com.leandro_fita.simplified_payment.model.transaction;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TransactionCreateDTO {

    @NotBlank
    private Double value;

    @NotBlank
    private Long payer;

    @NotBlank
    private Long payee;
}
