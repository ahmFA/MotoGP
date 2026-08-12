package api.ahm.motogp.championship.application.port.in;

import api.ahm.motogp.championship.application.port.query.ChampionshipTeamView;

import java.util.List;

public interface ListChampionshipTeamUseCase {
    List<ChampionshipTeamView> getChampionshipTeams(int championshipId);
    ChampionshipTeamView getChampionshipTeam(int championshipId, int championshipTeamId);
}
