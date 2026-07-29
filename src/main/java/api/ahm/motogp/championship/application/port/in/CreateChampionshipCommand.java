package api.ahm.motogp.championship.application.port.in;

public record CreateChampionshipCommand(
        Integer categoryId,
        Integer year
) {
}
