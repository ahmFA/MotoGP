package api.ahm.motogp.championship.application.service;

import api.ahm.motogp.championship.application.port.in.ListEventResultsUseCase;
import api.ahm.motogp.championship.application.port.out.EventResultRepositoryPort;
import api.ahm.motogp.championship.application.port.query.EventResultView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListEventResultsService implements ListEventResultsUseCase {

    private final EventResultRepositoryPort eventResultRepositoryPort;

    public ListEventResultsService(EventResultRepositoryPort eventResultRepositoryPort) {
        this.eventResultRepositoryPort = eventResultRepositoryPort;
    }


    @Override
    public List<EventResultView> getEventResults(int eventId) {
        return eventResultRepositoryPort.getEventResults(eventId);
    }
}
