package br.com.fiap.epictask.repository;

import br.com.fiap.epictask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String username);

    List<User> findAllByOrderByPointsDesc();
}
