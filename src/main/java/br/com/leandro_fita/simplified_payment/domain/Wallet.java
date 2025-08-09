package br.com.leandro_fita.simplified_payment.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@Entity(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wal_cd_id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "use_cd_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sto_cd_id")
    private Store store;

    @Column(name = "wal_num_balance")
    private Double balance;

    @CreationTimestamp
    @Column(name = "wal_dat_created_at", nullable = false)
    private OffsetDateTime createdAt;
}
