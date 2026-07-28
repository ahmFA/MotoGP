package api.ahm.motogp.rider.infrastructure.adapter.in.rest;

import api.ahm.motogp.rider.domain.model.Rider;

import java.util.Date;

public record RiderResponse(
        String name,
        Integer number,
        Date birthday,
        String countryName
) {

}
