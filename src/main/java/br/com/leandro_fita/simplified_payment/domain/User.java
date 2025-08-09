package br.com.leandro_fita.simplified_payment.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "use_cd_id", nullable = false)
    private Long id;

    @Column(name = "use_tx_full_name", nullable = false)
    private String fullName;

    @Column(name = "use_tx_email", nullable = false)
    private String email;

    @Column(name = "use_tx_password", nullable = false)
    private String password;

    @Column(name = "use_txt_cpf", nullable = false)
    private String cpf;

    @Column(name = "use_dat_created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "use_dat_updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "wo_cd_id", nullable = false, unique = true)
    private WalletOwner walletOwner;

    @OneToMany(mappedBy = "user")
    private Set<Store> stores;

}
