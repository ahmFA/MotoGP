package api.ahm.motogp.league.domain.model.valueobjects;


public record UserLeagueId(Long id) {

    public UserLeagueId{
        if(id==null || id <= 0){
            throw new IllegalArgumentException("User League ID must be greater than zero.");
        }
    }
}

