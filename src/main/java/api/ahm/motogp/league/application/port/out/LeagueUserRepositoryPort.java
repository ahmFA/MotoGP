package api.ahm.motogp.league.application.port.out;

public interface LeagueUserRepositoryPort {
    boolean existsUserById(long userId);
}
