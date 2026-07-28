package api.ahm.motogp.rider.infrastructure.adapter.out.persistence;

import api.ahm.motogp.rider.application.exception.RiderNotFoundException;
import api.ahm.motogp.rider.application.port.in.CreateRiderCommand;
import api.ahm.motogp.rider.application.port.in.FullUpdateRiderCommand;
import api.ahm.motogp.rider.application.port.out.RiderRepositoryPort;
import api.ahm.motogp.rider.domain.model.Rider;
import api.ahm.motogp.rider.infrastructure.adapter.in.rest.RiderResponse;
import api.ahm.motogp.rider.infrastructure.adapter.out.SpringDataRiderRepository;
import api.ahm.motogp.shared.country.exception.CountryNotFoundException;
import api.ahm.motogp.shared.country.infrastructure.adapter.out.persistence.CountryJPAEntity;
import api.ahm.motogp.shared.country.infrastructure.adapter.out.persistence.SpringDataCountryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RiderPersistenceAdapter  implements RiderRepositoryPort {

    private final SpringDataRiderRepository riderRepository;
    private final SpringDataCountryRepository countryRepository;

    public RiderPersistenceAdapter(SpringDataRiderRepository riderRepository, SpringDataCountryRepository countryRepository) {
        this.riderRepository = riderRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Rider> getRiders() {
        return riderRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Rider> getActiveRiders() {
        return riderRepository.findByActiveTrue().stream().map(this::toDomain).toList();
    }

    @Override
    public Optional<Rider> getRider(int id) {
        return riderRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Rider createRider(CreateRiderCommand riderCommand) {
        Optional<CountryJPAEntity> countryJPAEntity = countryRepository.findById(riderCommand.countryId());
        if(countryJPAEntity.isEmpty()){
            throw new CountryNotFoundException(riderCommand.countryId());
        }
        RiderJPAEntity riderJPAEntity = new RiderJPAEntity();
        riderJPAEntity.setId(0);
        riderJPAEntity.setName(riderCommand.name());
        riderJPAEntity.setNumber(riderCommand.number());
        riderJPAEntity.setBirthday(riderCommand.birthday());
        riderJPAEntity.setCountry(countryJPAEntity.get());
        riderJPAEntity.setActive(riderCommand.active());

        RiderJPAEntity newRiderJPAEntity = riderRepository.save(riderJPAEntity);
        return toDomain(newRiderJPAEntity);
    }

    @Override
    public Rider fullUpdateRider(FullUpdateRiderCommand riderCommand) {
        Optional<CountryJPAEntity> countryJPAEntity = countryRepository.findById(riderCommand.countryId());
        if(countryJPAEntity.isEmpty()){
            throw new CountryNotFoundException(riderCommand.countryId());
        }
        RiderJPAEntity newRiderJPAEntity = new RiderJPAEntity(
                riderCommand.id(),
                riderCommand.name(),
                riderCommand.number(),
                riderCommand.birthday(),
                countryJPAEntity.get(),
                riderCommand.active()
        );
        return toDomain(riderRepository.save(newRiderJPAEntity));

    }

    @Override
    public Rider deleteRider(Rider rider) {
        RiderJPAEntity riderJPAEntity = this.toEntity(rider);
        riderJPAEntity.setActive(false);
        return toDomain(riderRepository.save(riderJPAEntity));
    }

    @Override
    public Boolean existsRiderById(int id) {
        return riderRepository.existsById(id);
    }

    @Override
    public Boolean existsRiderByName(String name) {
        return riderRepository.existsRiderByName(name);
    }

    @Override
    public Boolean existsRiderByNumber(Integer number) {
        return riderRepository.existsRiderByNumber(number);
    }

    @Override
    public Boolean existsAnotherRiderByName(Integer id, String name){
        return riderRepository.existsRiderByIdNotAndName(id, name);
    }

    @Override
    public Boolean existsAnotherRiderByNumber(Integer myId, Integer number) {
        return riderRepository.existsRiderByIdNotAndNumber(myId, number);
    }

    private Rider toDomain(RiderJPAEntity rider) {
        return new Rider(
                rider.getId(),
                rider.getName(),
                rider.getNumber(),
                rider.getBirthday(),
                rider.getCountry().getId(),
                rider.getCountry().getName(),
                rider.isActive()
        );
    }

    private RiderResponse toResponse(RiderJPAEntity rider) {
        return new RiderResponse(
                rider.getName(),
                rider.getNumber(),
                rider.getBirthday(),
                rider.getCountry().getName()
        );
    }

    private RiderJPAEntity toEntity(Rider rider) {
        CountryJPAEntity countryJPAEntity = new CountryJPAEntity(rider.countryId(),  rider.countryName());
        return new RiderJPAEntity(
                rider.id(),
                rider.name(),
                rider.number(),
                rider.birthday(),
                countryJPAEntity,
                rider.active()
        );
    }
}
