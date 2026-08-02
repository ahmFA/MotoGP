package api.ahm.motogp.championship.application.port.in;

import api.ahm.motogp.championship.application.port.query.ChampionshipRiderView;

import java.util.List;

public interface ListChampionshipRiderUseCase {
    List<ChampionshipRiderView> getChampionshipRiders(int championshipId);
    ChampionshipRiderView getChampionshipRider(int championshipId, int championshipRiderId);
}
