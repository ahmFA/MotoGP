package api.ahm.motogp.championship.application.port.in.command;

import java.util.Date;

public record CreateChampionshipGrandPrixCommand(
        Integer grandPrixId,
        Integer championshipId,
        Date date,
        Integer roundNumber
) {
}
