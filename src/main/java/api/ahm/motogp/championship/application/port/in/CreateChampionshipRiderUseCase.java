package api.ahm.motogp.championship.application.port.in;

import api.ahm.motogp.championship.application.port.in.command.CreateChampionshipRiderCommand;
import api.ahm.motogp.championship.application.port.query.ChampionshipRiderView;

public interface CreateChampionshipRiderUseCase {
    ChampionshipRiderView addChampionshipRider(CreateChampionshipRiderCommand createChampionshipRiderCommand);
}
