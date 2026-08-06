package api.ahm.motogp.championship.application.port.in.command;

public record EventResultCommand(
        int championshipRiderId,
        int position,
        float points
) {
}
