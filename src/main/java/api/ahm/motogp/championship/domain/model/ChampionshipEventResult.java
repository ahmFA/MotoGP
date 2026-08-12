package api.ahm.motogp.championship.domain.model;

public record ChampionshipEventResult(
        int id,
        int championshipEventId,
        int championshipRiderId,
        int position,
        float points
) {

}
