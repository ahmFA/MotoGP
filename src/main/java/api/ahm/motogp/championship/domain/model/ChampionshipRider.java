package api.ahm.motogp.championship.domain.model;

public record ChampionshipRider(
        int championshipRiderId,
        int riderId,
        int teamId,
        int championshipId,
        int number

) {
    public ChampionshipRider {
        if(championshipRiderId <= 0){
            throw new IllegalArgumentException("championshipRiderId must be greater than 0");
        }
        if(riderId <= 0){
            throw new IllegalArgumentException("riderId must be greater than 0");
        }
        if(teamId <= 0){
            throw new IllegalArgumentException("teamId must be greater than 0");
        }
        if(championshipId <= 0){
            throw new IllegalArgumentException("championshipRiderId must be greater than 0");
        }
        if(number < 0){
            throw new IllegalArgumentException("number must be positive");
        }

    }
}
