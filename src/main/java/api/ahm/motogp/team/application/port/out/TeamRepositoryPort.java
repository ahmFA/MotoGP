package api.ahm.motogp.team.application.port.out;

import api.ahm.motogp.team.application.port.in.CreateTeamCommand;
import api.ahm.motogp.team.application.port.in.FullUpdateTeamCommand;
import api.ahm.motogp.team.domain.model.Team;

import java.util.List;
import java.util.Optional;

public interface TeamRepositoryPort {
    List<Team> getTeams();
    List<Team> getActiveTeams();
    Optional<Team> getTeam(int id);
    Boolean existsTeamById(int id);
    Boolean existsTeamByName(String name);
    Boolean existsAnotherTeamByName(Integer id, String name);
    Team createTeam(CreateTeamCommand teamCommand);
    Team fullUpdateTeam(FullUpdateTeamCommand teamCommand);
    Team deleteTeam(Team team);
}
