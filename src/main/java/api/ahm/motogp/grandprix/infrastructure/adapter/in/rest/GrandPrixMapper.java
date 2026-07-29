package api.ahm.motogp.grandprix.infrastructure.adapter.in.rest;

import api.ahm.motogp.grandprix.application.port.in.CreateGrandPrixCommand;
import api.ahm.motogp.grandprix.application.port.in.FullUpdateGrandPrixCommand;
import api.ahm.motogp.grandprix.domain.model.GrandPrix;

import java.util.ArrayList;
import java.util.List;

final class GrandPrixMapper {

    static CreateGrandPrixCommand toCommand(CreateGrandPrixRequest grandPrixRequest) {
        return new CreateGrandPrixCommand(
                grandPrixRequest.name(),
                grandPrixRequest.circuitName(),
                grandPrixRequest.countryId()
        );
    }

    static FullUpdateGrandPrixCommand toCommand(int id, PutGrandPrixRequest grandPrixRequest) {
        return new FullUpdateGrandPrixCommand(
                id,
                grandPrixRequest.name(),
                grandPrixRequest.circuitName(),
                grandPrixRequest.countryId()
        );
    }

    static GrandPrixResponse toResponse(GrandPrix grandPrix) {
        return new GrandPrixResponse(
                grandPrix.id(),
                grandPrix.name(),
                grandPrix.circuitName(),
                grandPrix.countryId(),
                grandPrix.countryName()
        );
    }

    static List<GrandPrixResponse> toResponse(List<GrandPrix> grandPrixes) {
        List<GrandPrixResponse> responses = new ArrayList<>();
        for (var grandPrix : grandPrixes) {
            responses.add(toResponse(grandPrix));
        }
        return responses;
    }
}
