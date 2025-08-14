package br.com.leandro_fita.simplified_payment.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@NoArgsConstructor
@Data
@Entity(name = "stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sto_cd_id", nullable = false)
    private Long id;

    @Column(name = "sto_tx_legal_name", nullable = false)
    private String legalName;

    @Column(name = "sto_tx_trade_name", nullable = false)
    private String tardeName;

    @Column(name = "sto_tx_cnpj", nullable = false)
    private String cnpj;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "use_cd_id")
    private User user;

    @CreationTimestamp
    @Column(name = "sto_dat_created_at", nullable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "sto_dat_updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "wo_cd_id", nullable = false, unique = true)
    private WalletOwner walletOwner;
}
