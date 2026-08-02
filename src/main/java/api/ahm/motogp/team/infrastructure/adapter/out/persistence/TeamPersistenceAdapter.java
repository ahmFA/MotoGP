package api.ahm.motogp.team.infrastructure.adapter.out.persistence;

import api.ahm.motogp.team.application.port.in.CreateTeamCommand;
import api.ahm.motogp.team.application.port.in.FullUpdateTeamCommand;
import api.ahm.motogp.team.application.port.out.TeamRepositoryPort;
import api.ahm.motogp.team.domain.model.Team;
import api.ahm.motogp.team.infrastructure.adapter.out.SpringDataTeamRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TeamPersistenceAdapter implements TeamRepositoryPort {

    private final SpringDataTeamRepository teamRepository;

    public TeamPersistenceAdapter(SpringDataTeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> getTeams() {
        return teamRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Team> getActiveTeams() {
        return teamRepository.findByActiveTrue()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Optional<Team> getTeam(int id) {
        return teamRepository.findById(id)
                .filter(TeamJPAEntity::isActive)
                .map(this::toDomain);
    }

    @Override
    public Boolean existsTeamById(int id) {
        return teamRepository.existsById(id);
    }

    @Override
    public Boolean existsTeamByName(String name) {
        return teamRepository.existsTeamByName(name);
    }

    @Override
    public Boolean existsAnotherTeamByName(Integer id, String name) {
        return teamRepository.existsTeamByIdNotAndName(id, name);
    }

    @Override
    public Team createTeam(CreateTeamCommand teamCommand) {
        TeamJPAEntity teamJPAEntity = new TeamJPAEntity();
        teamJPAEntity.setId(0);
        teamJPAEntity.setName(teamCommand.name());
        teamJPAEntity.setActive(teamCommand.active());

        return toDomain(teamRepository.save(teamJPAEntity));
    }

    @Override
    public Team fullUpdateTeam(FullUpdateTeamCommand teamCommand) {
        TeamJPAEntity teamJPAEntity = new TeamJPAEntity(
                teamCommand.id(),
                teamCommand.name(),
                teamCommand.active()
        );
        return toDomain(teamRepository.save(teamJPAEntity));
    }

    @Override
    public Team deleteTeam(Team team) {
        TeamJPAEntity teamJPAEntity = toEntity(team);
        teamJPAEntity.setActive(false);
        return toDomain(teamRepository.save(teamJPAEntity));
    }

    @Override
    public Boolean isActiveTeam(int teamId){
        return teamRepository.existsTeamByIdAndActiveTrue(teamId);
    }

    private Team toDomain(TeamJPAEntity team) {
        return new Team(
                team.getId(),
                team.getName(),
                team.isActive()
        );
    }

    private TeamJPAEntity toEntity(Team team) {
        return new TeamJPAEntity(
                team.id(),
                team.name(),
                team.active()
        );
    }
}
