package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

public record EventResultResponse(
        int position,
        int points,
        int championshipRiderId,
        String riderName,
        int number
) {
}
