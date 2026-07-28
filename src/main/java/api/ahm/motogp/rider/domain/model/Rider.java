package api.ahm.motogp.rider.domain.model;

import java.util.Date;

public record Rider (

    int id,
    String name,
    int number,
    Date birthday,
    int countryId,
    String countryName,
    boolean active
)
{
    public Rider {
        if(id <= 0)
            throw new IllegalArgumentException("ID must be positive");
        if(name.isBlank())
            throw new IllegalArgumentException("Name cannot be null or blank");
        if(number < 0)
            throw new IllegalArgumentException("Number cannot be negative");
        if(birthday == null)
            throw new IllegalArgumentException("Birthday cannot be null");
        if(countryId <= 0)
            throw new IllegalArgumentException("Country ID cannot be negative");
        if(countryName.isBlank())
            throw new IllegalArgumentException("Country Name cannot be null or blank");
    }

    public Rider create(int id, String name, int number, Date birthday, int countryId, String countryName, boolean active){
        return new Rider(id, name, number, birthday, countryId, countryName, active);
    }

}
