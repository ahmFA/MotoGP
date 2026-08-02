package api.ahm.motogp.championship.application.port.in.command;

public record CreateChampionshipCommand(
        Integer categoryId,
        Integer year
) {
}
