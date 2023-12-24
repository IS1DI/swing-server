package org.aist.swing_server.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.aist.swing_server.domain.Jewelry;
import org.aist.swing_server.repos.JewelryRepository;
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
public class JewelryService {
    private final JewelryRepository jewelryRepository;
    private final TypeService typeService;
    private final OrderRepository orderRepository;

    public List<Jewelry> findAll() {
        return jewelryRepository.findAll(Sort.by("id"));
    }

    public Jewelry get(UUID id) {
        return jewelryRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public Jewelry create(Jewelry jewelry) {
        jewelry.setType(typeService.get(jewelry.getType().getType()));
        return jewelryRepository.save(jewelry);
    }

    public <DTO> Jewelry update(UUID id, DTO dto, BiFunction<DTO,Jewelry,Jewelry> mapper) {
        return jewelryRepository.save(mapper.apply(dto,get(id)));
    }

    public void delete(UUID id) {
        Jewelry jewelry = get(id);
        orderRepository.findAllByJewelries(jewelry)
                .forEach(order -> order.getJewelries().remove(jewelry));
        jewelryRepository.delete(jewelry);
    }
}
