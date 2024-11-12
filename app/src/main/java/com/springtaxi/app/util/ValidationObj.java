package com.springtaxi.app.util;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationObj {
    private String message;
    private Map<String, String> errors;
}
