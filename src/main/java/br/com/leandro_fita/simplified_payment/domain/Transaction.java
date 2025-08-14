package br.com.leandro_fita.simplified_payment.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@Entity(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tra_cd_id", nullable = false)
    private Long id;

    @Column(name = "tra_num_value", nullable = false)
    private Double value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tra_cd_payer_wallet_id")
    private Wallet payer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tra_cd_payee_wallet_id")
    private Wallet payee;

    @CreationTimestamp
    @Column(name = "tra_dt_created_at", nullable = false)
    private OffsetDateTime createdAt;
}
