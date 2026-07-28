package api.ahm.motogp.rider.application.port.in;

import java.util.Date;

public record FullUpdateRiderCommand(
        Integer id,
        String name,
        Integer number,
        Date birthday,
        Integer countryId,
        Boolean active
) {
}
