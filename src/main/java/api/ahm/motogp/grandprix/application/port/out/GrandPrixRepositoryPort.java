package api.ahm.motogp.grandprix.application.port.out;

import api.ahm.motogp.grandprix.application.port.in.CreateGrandPrixCommand;
import api.ahm.motogp.grandprix.application.port.in.FullUpdateGrandPrixCommand;
import api.ahm.motogp.grandprix.domain.model.GrandPrix;

import java.util.List;
import java.util.Optional;

public interface GrandPrixRepositoryPort {
    List<GrandPrix> getGrandPrixes();
    Optional<GrandPrix> getGrandPrix(int id);
    Boolean existsGrandPrixById(int id);
    Boolean existsGrandPrixByName(String name);
    Boolean existsAnotherGrandPrixByName(Integer id, String name);
    GrandPrix createGrandPrix(CreateGrandPrixCommand grandPrixCommand);
    GrandPrix fullUpdateGrandPrix(FullUpdateGrandPrixCommand grandPrixCommand);
}
