package api.ahm.motogp.championship.application.port.in;

import api.ahm.motogp.championship.application.port.in.command.EventCommand;
import api.ahm.motogp.championship.application.port.query.ChampionshipGranPrixEventView;

import java.util.List;

public interface ListChampionshipEventUseCase {
    EventCommand getEvent(int eventId);
    List<ChampionshipGranPrixEventView> getEventsByChampionship(int championshipId);
}
