package api.ahm.motogp.championship.application.port.in;

import api.ahm.motogp.championship.application.port.query.ChampionshipGrandPrixView;

import java.util.List;

public interface ListChampionshipGrandPrixUseCase {
    List<ChampionshipGrandPrixView> getChampionshipGrandPrixes(int championshipId);
    ChampionshipGrandPrixView getChampionshipGrandPrix(int championshipId, int championshipGrandPrixId);
}
