package fi.dalitso.ticketsystem.repository;

import fi.dalitso.ticketsystem.domain.ModificationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModificationInfoRepository extends JpaRepository<ModificationInfo, Long> {
}
