package api.ahm.motogp.championship.application.port.out;

import api.ahm.motogp.championship.application.port.query.ChampionshipGrandPrixView;

import java.util.List;

public interface ChampionshipGrandPrixQueryPort {

    List<ChampionshipGrandPrixView> getChampionshipGrandPrixesResponse(int championshipId);
    ChampionshipGrandPrixView getChampionshipGrandPrixResponse(int championshipId, int championshipGrandPrixId);
}
