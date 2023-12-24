package org.aist.swing_server.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.aist.swing_server.domain.Order;
import org.aist.swing_server.repos.OrderRepository;
import org.aist.swing_server.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;


@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll(Sort.by("id"));
    }

    public Order get(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public List<Order> findByUserName(String username) {
        return orderRepository.findByOwnerIgnoreCaseOrderByOwnerAsc(username);
    }

    public Order create(String owner,Order order) {
        order.setOwner(owner);
        return orderRepository.save(order);
    }

    public <DTO> Order update(UUID id, DTO dto, BiFunction<DTO,Order,Order> mapper) {
        return orderRepository.save(mapper.apply(dto,mapper.apply(dto,get(id))));
    }

    public void delete(UUID id) {
        orderRepository.delete(get(id));
    }
}
