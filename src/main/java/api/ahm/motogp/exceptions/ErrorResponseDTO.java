package api.ahm.motogp.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponseDTO (
    LocalDateTime timestamp,
    int status,
    Map<String, String> errors
)
{}
