package br.com.leandro_fita.simplified_payment.domain;

import br.com.leandro_fita.simplified_payment.domain.enums.OwnerType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity(name = "wallet_owners")
public class WalletOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wal_cd_id")
    private Long id;

    @Column(name = "wo_txt_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private OwnerType ownerType;
}
