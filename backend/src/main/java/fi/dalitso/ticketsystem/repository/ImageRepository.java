package fi.dalitso.ticketsystem.repository;

import fi.dalitso.ticketsystem.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
