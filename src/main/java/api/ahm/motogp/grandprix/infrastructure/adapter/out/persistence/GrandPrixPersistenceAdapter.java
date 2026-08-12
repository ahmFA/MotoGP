package api.ahm.motogp.grandprix.infrastructure.adapter.out.persistence;

import api.ahm.motogp.grandprix.application.port.in.CreateGrandPrixCommand;
import api.ahm.motogp.grandprix.application.port.in.FullUpdateGrandPrixCommand;
import api.ahm.motogp.grandprix.application.port.out.GrandPrixRepositoryPort;
import api.ahm.motogp.grandprix.domain.model.GrandPrix;
import api.ahm.motogp.grandprix.infrastructure.adapter.out.SpringDataGrandPrixRepository;
import api.ahm.motogp.shared.country.exception.CountryNotFoundException;
import api.ahm.motogp.shared.country.infrastructure.adapter.out.persistence.CountryJPAEntity;
import api.ahm.motogp.shared.country.infrastructure.adapter.out.persistence.SpringDataCountryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GrandPrixPersistenceAdapter implements GrandPrixRepositoryPort {

    private final SpringDataGrandPrixRepository grandPrixRepository;
    private final SpringDataCountryRepository countryRepository;

    public GrandPrixPersistenceAdapter(SpringDataGrandPrixRepository grandPrixRepository,
                                       SpringDataCountryRepository countryRepository) {
        this.grandPrixRepository = grandPrixRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<GrandPrix> getGrandPrixes() {
        return grandPrixRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Optional<GrandPrix> getGrandPrix(int id) {
        return grandPrixRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Boolean existsGrandPrixById(int id) {
        return grandPrixRepository.existsById(id);
    }

    @Override
    public Boolean existsGrandPrixByName(String name) {
        return grandPrixRepository.existsGrandPrixByName(name);
    }

    @Override
    public Boolean existsAnotherGrandPrixByName(Integer id, String name) {
        return grandPrixRepository.existsGrandPrixByIdNotAndName(id, name);
    }

    @Override
    public GrandPrix createGrandPrix(CreateGrandPrixCommand grandPrixCommand) {
        CountryJPAEntity country = getCountry(grandPrixCommand.countryId());
        GrandPrixJPAEntity grandPrixJPAEntity = new GrandPrixJPAEntity();
        grandPrixJPAEntity.setId(0);
        grandPrixJPAEntity.setName(grandPrixCommand.name());
        grandPrixJPAEntity.setCircuitName(grandPrixCommand.circuitName());
        grandPrixJPAEntity.setCountry(country);

        return toDomain(grandPrixRepository.save(grandPrixJPAEntity));
    }

    @Override
    public GrandPrix fullUpdateGrandPrix(FullUpdateGrandPrixCommand grandPrixCommand) {
        CountryJPAEntity country = getCountry(grandPrixCommand.countryId());
        GrandPrixJPAEntity grandPrixJPAEntity = new GrandPrixJPAEntity(
                grandPrixCommand.id(),
                grandPrixCommand.name(),
                grandPrixCommand.circuitName(),
                country
        );
        return toDomain(grandPrixRepository.save(grandPrixJPAEntity));
    }

    private CountryJPAEntity getCountry(Integer countryId) {
        return countryRepository.findById(countryId)
                .orElseThrow(() -> new CountryNotFoundException(countryId));
    }

    private GrandPrix toDomain(GrandPrixJPAEntity grandPrix) {
        return new GrandPrix(
                grandPrix.getId(),
                grandPrix.getName(),
                grandPrix.getCircuitName(),
                grandPrix.getCountry().getId(),
                grandPrix.getCountry().getName()
        );
    }
}
