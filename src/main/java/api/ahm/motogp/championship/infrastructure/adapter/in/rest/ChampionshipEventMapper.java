package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

import api.ahm.motogp.championship.application.port.in.command.CreateChampionshipEventCommand;
import api.ahm.motogp.championship.application.port.in.command.EventCommand;
import api.ahm.motogp.championship.application.port.query.ChampionshipGranPrixEventView;

final class ChampionshipEventMapper {

    private ChampionshipEventMapper() {
    }

    static CreateChampionshipEventCommand toCommand(CreateChampionshipEventBulkRequest eventsBulk, int championshipId) {
        return new CreateChampionshipEventCommand(
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
                toResponseEventType(eventCommand.eventType()),
                eventCommand.startDate(),
                ""
        );
    }

    static ChampionshipEventResponse toResponse(ChampionshipGranPrixEventView eventCommand) {
        return new ChampionshipEventResponse(
                eventCommand.id(),
                toResponseEventType(eventCommand.eventType()),
                eventCommand.startDate(),
                eventCommand.grandPrixName()
        );
    }

    private static EventCommand toCommand(CreateChampionshipEventBulkRequest.EventEntry event) {
        return new EventCommand(
                null,
                event.championshipGrandPrixId(),
                toCommandEventType(event.eventType()),
                event.startDate()
        );
    }

    private static EventCommand.EventType toCommandEventType(CreateChampionshipEventBulkRequest.EventType eventType) {
        return EventCommand.EventType.valueOf(eventType.name());
    }

    private static ChampionshipEventResponse.EventType toResponseEventType(EventCommand.EventType eventType) {
        return ChampionshipEventResponse.EventType.valueOf(eventType.name());
    }

    private static ChampionshipEventResponse.EventType toResponseEventType(ChampionshipGranPrixEventView.EventType eventType) {
        return ChampionshipEventResponse.EventType.valueOf(eventType.name());
    }
}
