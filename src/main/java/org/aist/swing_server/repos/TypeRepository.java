package org.aist.swing_server.repos;

import org.aist.swing_server.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TypeRepository extends JpaRepository<Type, String> {

    boolean existsByTypeIgnoreCase(String type);

}
