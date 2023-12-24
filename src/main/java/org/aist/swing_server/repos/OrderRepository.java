package org.aist.swing_server.repos;

import org.aist.swing_server.domain.Jewelry;
import org.aist.swing_server.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findAllByJewelries(Jewelry jewelry);

    List<Order> findByOwnerIgnoreCaseOrderByOwnerAsc(String owner);
}
