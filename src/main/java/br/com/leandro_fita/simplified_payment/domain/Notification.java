package br.com.leandro_fita.simplified_payment.domain;

import br.com.leandro_fita.simplified_payment.domain.enums.NotificationStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "not_cd_id", nullable = false)
    private Long id;

    @Column(name = "not_txt_status", nullable = false)
    private NotificationStatus status;

    @Column(name = "not_txt_message", nullable = false)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tra_cd_id", nullable = false)
    private Transaction transaction;

    @CreationTimestamp
    @Column(name = "not_dt_sent_at", nullable = false)
    private OffsetDateTime sentAt;
}
