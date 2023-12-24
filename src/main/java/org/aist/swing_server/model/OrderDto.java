package org.aist.swing_server.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class OrderDto {
    @Getter
    @Setter
    public static final class Create {
        private LocalTime createdAt;
        private LocalTime closedAt;
        private Status status;
        private List<UUID> jewelries;
    }
    @Getter
    @Setter
    public static final class Update {
        private LocalTime createdAt;
        private LocalTime closedAt;
        private Status status;
        private List<UUID> jewelries;
    }
    @Getter
    @Setter
    public static final class Output {
        private UUID id;
        private LocalTime createdAt;
        private LocalTime closedAt;
        private Status status;
        private List<UUID> jewelries;
        private String owner;
    }
}

