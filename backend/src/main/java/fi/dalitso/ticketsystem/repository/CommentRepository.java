package fi.dalitso.ticketsystem.repository;

import fi.dalitso.ticketsystem.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
