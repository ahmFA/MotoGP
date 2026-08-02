package api.ahm.motogp.championship.application.port.in.command;

public record CreateChampionshipRiderCommand (
    Integer riderId,
    Integer teamId,
    Integer championshipId,
    Integer number
){
}
