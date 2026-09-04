package api.ahm.motogp.championship.application.port.query;

public record EventResultView(
        int id,
        int position,
        float points,
        int championshipRiderId,
        String riderName,
        int number
) {
}
