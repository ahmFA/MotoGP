package api.ahm.motogp.championship.domain.model;

import java.util.Date;

public record ChampionshipGrandPrix(
        int championshipGrandPrixId,
        int grandPrixId,
        int championshipId,
        Date date,
        int roundNumber
) {
}
