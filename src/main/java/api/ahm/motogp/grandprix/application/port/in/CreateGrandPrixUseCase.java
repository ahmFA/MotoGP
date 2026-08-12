package api.ahm.motogp.grandprix.application.port.in;

import api.ahm.motogp.grandprix.domain.model.GrandPrix;

public interface CreateGrandPrixUseCase {
    GrandPrix createGrandPrix(CreateGrandPrixCommand grandPrixCommand);
}
