package api.ahm.motogp.championship.application.service;

import api.ahm.motogp.championship.application.port.query.EventResultView;
import api.ahm.motogp.championship.domain.model.EventResult;

public class EventResultMapper {

    public static EventResult toDomain(EventResultView view, int championshipEventId){
        return new EventResult(
                view.id(),
                championshipEventId,
                view.championshipRiderId(),
                view.position(),
                view.points()
        );
    }

    public static EventResultView toView(EventResult domain, String riderName, int number){
        return new EventResultView(
                domain.id(),
                domain.position(),
                domain.points(),
                domain.championshipRiderId(),
                riderName,
                number
        );
    }
}
