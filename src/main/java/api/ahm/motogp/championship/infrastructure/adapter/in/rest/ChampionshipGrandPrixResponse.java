package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

import java.util.Date;

public record ChampionshipGrandPrixResponse(
        int id,
        int grandPrixId,
        String grandPrixName,
        String circuitName,
        Date date,
        int roundNumber
) {
}
