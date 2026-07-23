package api.ahm.motogp.dto;

import java.util.Date;

public record CreateRiderRequest(
        String name,
        Integer number,
        Date birthday,
        Integer countryId
) {
}
