package org.aist.swing_server.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TypeDto {
    @Size(max = 255)
    private String type;
}
