package br.com.leandro_fita.simplified_payment.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_cd_id", nullable = false)
    private Integer id;

    @Column(name = "pro_tx_name", nullable = false)
    private String name;

    @Column(name = "pro_tx_description", nullable = false)
    private String description;
}
