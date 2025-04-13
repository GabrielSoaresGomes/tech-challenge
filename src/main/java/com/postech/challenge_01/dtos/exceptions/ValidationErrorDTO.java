package com.postech.challenge_01.dtos.exceptions;

import java.util.List;

public record ValidationErrorDTO(List<String> errors, int status) {
}
