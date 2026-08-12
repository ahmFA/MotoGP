package api.ahm.motogp.shared.constructor.infrastructure.adapter.out.persistance;

import api.ahm.motogp.championship.application.port.out.ConstructorRepositoryPort;
import org.springframework.stereotype.Repository;

@Repository
public class ConstructorPersistenceAdapter implements ConstructorRepositoryPort {

    private final SpringDataConstructorRepository constructorRepository;

    public ConstructorPersistenceAdapter(SpringDataConstructorRepository constructorRepository) {
        this.constructorRepository = constructorRepository;
    }
    public boolean existsConstructorById(int id) {
        return constructorRepository.existsById(id);
    }
}
