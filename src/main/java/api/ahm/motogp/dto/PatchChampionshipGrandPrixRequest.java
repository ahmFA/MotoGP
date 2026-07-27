package api.ahm.motogp.dto;

import java.util.Date;

public record PatchChampionshipGrandPrixRequest(
        Date date,
        Integer roundNumber
) {
}
