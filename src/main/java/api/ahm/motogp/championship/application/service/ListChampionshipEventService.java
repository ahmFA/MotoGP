package api.ahm.motogp.championship.application.service;

import api.ahm.motogp.championship.application.port.in.ListChampionshipEventUseCase;
import api.ahm.motogp.championship.application.port.in.command.EventCommand;
import api.ahm.motogp.championship.application.port.out.ChampionshipEventRepositoryPort;
import api.ahm.motogp.championship.application.port.query.ChampionshipEventView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListChampionshipEventService  implements ListChampionshipEventUseCase {

    private final ChampionshipEventRepositoryPort championshipEventRepositoryPort;

    public ListChampionshipEventService(ChampionshipEventRepositoryPort championshipEventRepositoryPort) {
        this.championshipEventRepositoryPort = championshipEventRepositoryPort;
    }

    public EventCommand getEvent(int eventId){
        return championshipEventRepositoryPort.getEventByEventId(eventId);
    }

    public List<ChampionshipEventView> getEventsByChampionship(int championshipId){
        return championshipEventRepositoryPort.getEventsByChampionship(championshipId);
    }
}
