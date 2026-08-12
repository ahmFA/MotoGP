package api.ahm.motogp.championship.application.port.out;

import api.ahm.motogp.championship.application.port.query.ChampionshipRiderView;

import java.util.List;

public interface ChampionshipRiderQueryPort {

    List<ChampionshipRiderView> getChampionshipRidersResponse(int championshipId);
    ChampionshipRiderView getChampionshipRiderResponse(int championshipId, int championshipRiderId);
}
