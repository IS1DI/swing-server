package org.aist.swing_server.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Getter
@Setter
public class Type {
    @Id
    @Column(nullable = false, updatable = false)
    private String type;
    @OneToMany(mappedBy = "type")
    private Set<Jewelry> jewelries;
}
