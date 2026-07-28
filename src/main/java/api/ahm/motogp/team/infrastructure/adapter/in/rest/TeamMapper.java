package api.ahm.motogp.team.infrastructure.adapter.in.rest;

import api.ahm.motogp.team.application.port.in.CreateTeamCommand;
import api.ahm.motogp.team.application.port.in.FullUpdateTeamCommand;
import api.ahm.motogp.team.domain.model.Team;

import java.util.ArrayList;
import java.util.List;

final class TeamMapper {

    static CreateTeamCommand toCommand(CreateTeamRequest teamRequest) {
        return new CreateTeamCommand(
                teamRequest.name(),
                teamRequest.active()
        );
    }

    static FullUpdateTeamCommand toCommand(int id, PutTeamRequest teamRequest) {
        return new FullUpdateTeamCommand(
                id,
                teamRequest.name(),
                teamRequest.active()
        );
    }

    static TeamResponse toResponse(Team team) {
        return new TeamResponse(
                team.id(),
                team.name(),
                team.active()
        );
    }

    static List<TeamResponse> toResponse(List<Team> teams) {
        List<TeamResponse> responses = new ArrayList<>();
        for (var team : teams) {
            responses.add(toResponse(team));
        }
        return responses;
    }
}
