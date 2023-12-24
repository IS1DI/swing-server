package org.aist.swing_server.repos;

import org.aist.swing_server.domain.Jewelry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface JewelryRepository extends JpaRepository<Jewelry, UUID> {
}
