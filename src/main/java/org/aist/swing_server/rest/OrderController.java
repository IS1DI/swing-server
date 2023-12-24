package org.aist.swing_server.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aist.swing_server.domain.Order;
import org.aist.swing_server.mapper.OrderMapper;
import org.aist.swing_server.model.OrderDto;
import org.aist.swing_server.model.Status;
import org.aist.swing_server.service.OrderService;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/api/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping
    public List<OrderDto.Output> getAllOrders(Authentication authentication, @RequestParam(required = false, name = "u", defaultValue = "") String user) {
        if (authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(it -> it.equals("ADMIN")))
            if (user.isBlank())
                return orderService.findAll().stream().map(orderMapper::toOutput).collect(Collectors.toList());
            else
                return orderService.findByUserName(user).stream().map(orderMapper::toOutput).collect(Collectors.toList());
        else {
            return orderService.findByUserName(authentication.getName()).stream().map(orderMapper::toOutput).collect(Collectors.toList());
        }
    }

    @GetMapping("/{id}")
    public OrderDto.Output getOrder(Authentication authentication,@PathVariable UUID id) {
        Order order = orderService.get(id);
        if(order.getOwner().equals(authentication.getName()) || authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(it -> it.equals("ADMIN"))) {
            return orderMapper.toOutput(order);
        } else throw new AccessDeniedException("access denied for this resource");
    }

    @PostMapping
    public OrderDto.Output createOrder(Authentication authentication,@RequestBody @Valid OrderDto.Create order) {
        return orderMapper.toOutput(orderService.create(authentication.getName(),orderMapper.toEntity(order)));
    }

    @PutMapping("/{id}")
    @PostAuthorize("#returnObject.owner.equals(authentication.name) or hasRole('ADMIN')")
    public OrderDto.Output updateOrder(@PathVariable UUID id,
                                       @RequestBody @Valid final OrderDto.Update orderDTO) {
        return orderMapper.toOutput(orderService.update(id, orderDTO, orderMapper::toUpdate));
    }

    @GetMapping("statuses")
    public List<String> statuses() {
        return Arrays.stream(Status.values()).map(Enum::name).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable UUID id) {
        orderService.delete(id);
    }

}
