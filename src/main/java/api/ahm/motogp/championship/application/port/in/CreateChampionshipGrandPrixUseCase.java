package api.ahm.motogp.championship.application.port.in;

import api.ahm.motogp.championship.application.port.in.command.CreateChampionshipGrandPrixCommand;
import api.ahm.motogp.championship.application.port.query.ChampionshipGrandPrixView;

public interface CreateChampionshipGrandPrixUseCase {
    ChampionshipGrandPrixView addChampionshipGrandPrix(CreateChampionshipGrandPrixCommand createChampionshipGrandPrixCommand);
}
