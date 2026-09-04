package api.ahm.motogp.championship.application.port.in;

import api.ahm.motogp.championship.application.port.query.EventResultView;

import java.util.List;

public interface ListEventResultsUseCase {
    List<EventResultView> getEventResults(int eventId);
}
