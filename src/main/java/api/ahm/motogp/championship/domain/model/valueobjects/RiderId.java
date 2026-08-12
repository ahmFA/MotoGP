package api.ahm.motogp.championship.domain.model.valueobjects;

import api.ahm.motogp.rider.domain.model.Rider;

public record RiderId(Long id) {
    public RiderId{
        if(id==null || id <= 0){
            throw new IllegalArgumentException("User ID must be greater than zero.");
        }
    }
}
