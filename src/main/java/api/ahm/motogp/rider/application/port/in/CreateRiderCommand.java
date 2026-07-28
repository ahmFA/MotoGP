package api.ahm.motogp.rider.application.port.in;


import java.util.Date;

public record CreateRiderCommand(
        String name,
        Integer number,
        Date birthday,
        Integer countryId,
        Boolean active
) {
}
