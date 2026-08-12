package api.ahm.motogp.championship.application.port.query;

public record ChampionshipRiderView(
        int id,
        int riderId,
        String riderName,
        int teamId,
        String teamName,
        int number
) {
}
