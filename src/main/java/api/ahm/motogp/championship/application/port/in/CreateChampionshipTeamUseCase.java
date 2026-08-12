package api.ahm.motogp.championship.application.port.in;

import api.ahm.motogp.championship.application.port.in.command.CreateChampionshipTeamCommand;
import api.ahm.motogp.championship.application.port.query.ChampionshipTeamView;

public interface CreateChampionshipTeamUseCase {
    ChampionshipTeamView addChampionshipTeam(CreateChampionshipTeamCommand createChampionshipTeamCommand);
}
