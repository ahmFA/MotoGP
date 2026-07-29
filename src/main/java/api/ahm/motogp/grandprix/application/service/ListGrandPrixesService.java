package api.ahm.motogp.grandprix.application.service;

import api.ahm.motogp.grandprix.application.exception.GrandPrixNotFoundException;
import api.ahm.motogp.grandprix.application.port.in.ListGrandPrixUseCase;
import api.ahm.motogp.grandprix.application.port.out.GrandPrixRepositoryPort;
import api.ahm.motogp.grandprix.domain.model.GrandPrix;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ListGrandPrixesService implements ListGrandPrixUseCase {

    private final GrandPrixRepositoryPort grandPrixRepositoryPort;

    public ListGrandPrixesService(GrandPrixRepositoryPort grandPrixRepositoryPort) {
        this.grandPrixRepositoryPort = grandPrixRepositoryPort;
    }

    @Override
    public List<GrandPrix> getGrandPrixes() {
        return grandPrixRepositoryPort.getGrandPrixes();
    }

    @Override
    public Optional<GrandPrix> getGrandPrix(int id) {
        Optional<GrandPrix> grandPrix = grandPrixRepositoryPort.getGrandPrix(id);
        if (grandPrix.isEmpty()) {
            throw new GrandPrixNotFoundException(id);
        }
        return grandPrix;
    }
}
