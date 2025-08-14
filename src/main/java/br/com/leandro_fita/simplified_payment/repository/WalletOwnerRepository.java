package br.com.leandro_fita.simplified_payment.repository;

import br.com.leandro_fita.simplified_payment.domain.WalletOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletOwnerRepository extends JpaRepository<WalletOwner, Long> {
}
