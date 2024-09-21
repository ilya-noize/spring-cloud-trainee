package tech.sdhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.sdhub.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
