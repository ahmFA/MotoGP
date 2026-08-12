package api.ahm.motogp.shared.infrastructure.adapter.in;

import java.time.LocalDateTime;
import java.util.Map;

public record GlobalErrorResponse(
        LocalDateTime localDateTime,
        int status,
        Map<String, String> errors
) {
}
