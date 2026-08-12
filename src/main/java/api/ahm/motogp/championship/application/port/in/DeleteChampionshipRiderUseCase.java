package api.ahm.motogp.championship.application.port.in;

public interface DeleteChampionshipRiderUseCase {
    void deleteChampionshipRider(int championshipId, int championshipRiderId);
}
