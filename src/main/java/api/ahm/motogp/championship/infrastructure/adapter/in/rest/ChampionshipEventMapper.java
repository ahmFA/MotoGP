package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

import api.ahm.motogp.championship.application.port.in.command.CreateEventCommand;
import api.ahm.motogp.championship.application.port.in.command.EventCommand;
import api.ahm.motogp.championship.application.port.query.EventView;

final class ChampionshipEventMapper {

    private ChampionshipEventMapper() {
    }

    static CreateEventCommand toCommand(CreateChampionshipEventBulkRequest eventsBulk, int championshipId) {
        return new CreateEventCommand(
                championshipId,
                eventsBulk.events()
                        .stream()
                        .map(ChampionshipEventMapper::toCommand)
                        .toList()
        );
    }

    static ChampionshipEventResponse toResponse(EventCommand eventCommand) {
        return new ChampionshipEventResponse(
                eventCommand.id(),
                eventCommand.eventType(),
                eventCommand.startDate(),
                ""
        );
    }

    static ChampionshipEventResponse toResponse(EventView eventCommand) {
        return new ChampionshipEventResponse(
                eventCommand.id(),
                eventCommand.eventType(),
                eventCommand.startDate(),
                eventCommand.grandPrixName()
        );
    }

    private static EventCommand toCommand(CreateChampionshipEventBulkRequest.EventEntry event) {
        return new EventCommand(
                null,
                event.championshipGrandPrixId(),
                event.eventType(),
                event.startDate(),
                event.eventStatus()
        );
    }
}
