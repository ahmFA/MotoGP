package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

import api.ahm.motogp.championship.application.port.in.command.CreateChampionshipCommand;
import api.ahm.motogp.championship.domain.model.Championship;

import java.util.ArrayList;
import java.util.List;

final class ChampionshipMapper {

    static CreateChampionshipCommand toCommand(CreateChampionshipRequest championshipRequest) {
        return new CreateChampionshipCommand(
                championshipRequest.categoryId(),
                championshipRequest.year()
        );
    }

    static ChampionshipResponse toResponse(Championship championship) {
        return new ChampionshipResponse(
                championship.id(),
                championship.categoryId(),
                championship.categoryName(),
                championship.year()
        );
    }

    static List<ChampionshipResponse> toResponse(List<Championship> championships) {
        List<ChampionshipResponse> responses = new ArrayList<>();
        for (var championship : championships) {
            responses.add(toResponse(championship));
        }
        return responses;
    }
}
