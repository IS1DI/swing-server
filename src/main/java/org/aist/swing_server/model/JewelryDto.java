package org.aist.swing_server.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class JewelryDto {
    @Getter
    @Setter
    public static final class Create {
        @NotNull
        @Size(max = 255)
        private String name;
        @Size(max = 255)
        private String color;
        private Double size;
        @NotNull
        @Size(max = 255)
        private String type;
    }
    @Getter
    @Setter
    public static final class Update {
        @NotNull
        @Size(max = 255)
        private String name;
        @Size(max = 255)
        private String color;
        private Double size;
        @NotNull
        @Size(max = 255)
        private String type;
    }
    @Getter
    @Setter
    public static final class Output {
        private UUID id;
        @NotNull
        @Size(max = 255)
        private String name;
        @Size(max = 255)
        private String color;
        private Double size;
        @NotNull
        @Size(max = 255)
        private String type;
    }
}
