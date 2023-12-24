package org.aist.swing_server.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FieldError {
    private String field;
    private String errorCode;
}
