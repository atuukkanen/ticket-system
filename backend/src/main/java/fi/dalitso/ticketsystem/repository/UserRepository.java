package fi.dalitso.ticketsystem.repository;

import fi.dalitso.ticketsystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
