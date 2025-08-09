package br.com.leandro_fita.simplified_payment.domain;

import br.com.leandro_fita.simplified_payment.domain.enums.OwnerType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity(name = "wallet_owner")
public class WalletOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wal_cd_id")
    private Long id;

    @Column(name = "wo_txt_type", nullable = false)
    private OwnerType ownerType;
}
