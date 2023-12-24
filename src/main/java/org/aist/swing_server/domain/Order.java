package org.aist.swing_server.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.aist.swing_server.model.Status;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalTime createdAt;
    private LocalTime closedAt;
    @Column
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToMany
    @JoinTable(
            name = "OrderJewelry",
            joinColumns = @JoinColumn(name = "orderId"),
            inverseJoinColumns = @JoinColumn(name = "jewelryId")
    )
    private Set<Jewelry> jewelries = new HashSet<>();
    private String owner;
}
