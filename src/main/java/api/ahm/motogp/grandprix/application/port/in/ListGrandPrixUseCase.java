package api.ahm.motogp.grandprix.application.port.in;

import api.ahm.motogp.grandprix.domain.model.GrandPrix;

import java.util.List;
import java.util.Optional;

public interface ListGrandPrixUseCase {
    List<GrandPrix> getGrandPrixes();
    Optional<GrandPrix> getGrandPrix(int id);
}
