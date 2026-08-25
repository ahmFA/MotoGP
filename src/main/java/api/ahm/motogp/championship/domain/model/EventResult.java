package api.ahm.motogp.championship.domain.model;

public record EventResult(
        int id,
        int championshipEventId,
        int championshipRiderId,
        int position,
        float points
) {

}
