package api.ahm.motogp.championship.application.port.out;

import api.ahm.motogp.championship.application.port.in.command.CreateChampionshipTeamCommand;
import api.ahm.motogp.championship.application.port.query.ChampionshipTeamView;

public interface ChampionshipTeamRepositoryPort {
    boolean existsChampionshipTeamById(int championshipTeamId);
    boolean existsChampionshipTeamByChampionshipIdAndId(int championshipId, int championshipTeamId);
    boolean existsChampionshipTeamByChampionshipIdAndTeamId(int championshipId, int teamId);
    boolean existsChampionshipTeamByChampionshipIdAndName(int championshipId, String name);
    ChampionshipTeamView createChampionshipTeam(CreateChampionshipTeamCommand command);
    void deleteChampionshipTeamById(int championshipTeamId);
}
