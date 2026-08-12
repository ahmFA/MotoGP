package api.ahm.motogp.championship.application.port.query;

import java.util.Date;

public record ChampionshipGrandPrixView(
        int id,
        int grandPrixId,
        String grandPrixName,
        String circuitName,
        Date date,
        int roundNumber
) {
}
