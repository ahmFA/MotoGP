package api.ahm.motogp.league.infrastructure.adapter.in.rest;

import api.ahm.motogp.identity.domain.model.valueobjects.UserId;
import api.ahm.motogp.league.application.port.in.command.CreateUserLeagueCommand;
import api.ahm.motogp.league.application.port.query.UserLeagueView;

import java.util.ArrayList;
import java.util.List;

final class UserLeagueMapper {

    private UserLeagueMapper() {
    }

    static CreateUserLeagueCommand toCommand(long leagueId, long userId) {
        return new CreateUserLeagueCommand(
                leagueId,
                new UserId(userId)
        );
    }

    static UserLeagueResponse toResponse(UserLeagueView userLeagueView) {
        return new UserLeagueResponse(
                userLeagueView.id(),
                userLeagueView.leagueId(),
                userLeagueView.userId(),
                userLeagueView.username(),
                userLeagueView.email(),
                userLeagueView.role()
        );
    }

    static List<UserLeagueResponse> toResponse(List<UserLeagueView> userLeagueViews) {
        List<UserLeagueResponse> responses = new ArrayList<>();
        for (var userLeagueView : userLeagueViews) {
            responses.add(toResponse(userLeagueView));
        }
        return responses;
    }
}
