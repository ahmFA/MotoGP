package api.ahm.motogp.championship.application.port.out;

import api.ahm.motogp.championship.application.port.query.ChampionshipTeamView;

import java.util.List;

public interface ChampionshipTeamQueryPort {

    List<ChampionshipTeamView> getChampionshipTeamsResponse(int championshipId);
    ChampionshipTeamView getChampionshipTeamResponse(int championshipId, int championshipTeamId);
}
