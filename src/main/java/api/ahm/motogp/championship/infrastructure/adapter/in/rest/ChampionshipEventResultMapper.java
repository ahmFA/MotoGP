package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

import api.ahm.motogp.championship.application.port.in.command.CreateEventResultCommand;
import api.ahm.motogp.championship.application.port.in.command.EventResultCommand;

final class ChampionshipEventResultMapper {

    private ChampionshipEventResultMapper() {
    }

    static CreateEventResultCommand toCommand(CreateChampionshipEventResultBulkRequest request,
                                              int eventId) {
        return new CreateEventResultCommand(
                eventId,
                request.results()
                        .stream()
                        .map(ChampionshipEventResultMapper::toCommand)
                        .toList()
        );
    }

    private static EventResultCommand toCommand(CreateChampionshipEventResultBulkRequest.ResultEntry result) {
        return new EventResultCommand(
                result.championshipRiderId(),
                result.position(),
                result.points()
        );
    }
}
