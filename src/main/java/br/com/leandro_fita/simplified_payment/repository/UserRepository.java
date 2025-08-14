package br.com.leandro_fita.simplified_payment.repository;

import br.com.leandro_fita.simplified_payment.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByWalletOwner_Id(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByCpf(String cpf);
}
