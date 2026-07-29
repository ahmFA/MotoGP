package api.ahm.motogp.championship.infrastructure.adapter.out.persistence;

import api.ahm.motogp.championship.application.port.in.CreateChampionshipCommand;
import api.ahm.motogp.championship.application.port.out.ChampionshipRepositoryPort;
import api.ahm.motogp.championship.domain.model.Championship;
import api.ahm.motogp.championship.infrastructure.adapter.out.SpringDataChampionshipRepository;
import api.ahm.motogp.shared.category.exception.CategoryNotFoundException;
import api.ahm.motogp.shared.category.infrastructure.adapter.out.persistence.CategoryJPAEntity;
import api.ahm.motogp.shared.category.infrastructure.adapter.out.persistence.SpringDataCategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ChampionshipPersistenceAdapter implements ChampionshipRepositoryPort {

    private final SpringDataChampionshipRepository championshipRepository;
    private final SpringDataCategoryRepository categoryRepository;

    public ChampionshipPersistenceAdapter(SpringDataChampionshipRepository championshipRepository,
                                          SpringDataCategoryRepository categoryRepository) {
        this.championshipRepository = championshipRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Championship> getChampionships() {
        return championshipRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Optional<Championship> getChampionship(int id) {
        return championshipRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Boolean existsChampionshipById(int id) {
        return championshipRepository.existsById(id);
    }

    @Override
    public Boolean existsChampionshipByCategoryIdAndYear(Integer categoryId, Integer year) {
        return championshipRepository.existsByCategoryIdAndYear(categoryId, year);
    }

    @Override
    public Championship createChampionship(CreateChampionshipCommand championshipCommand) {
        CategoryJPAEntity category = getCategory(championshipCommand.categoryId());
        ChampionshipJPAEntity championshipJPAEntity = new ChampionshipJPAEntity();
        championshipJPAEntity.setId(0);
        championshipJPAEntity.setCategory(category);
        championshipJPAEntity.setYear(championshipCommand.year());

        return toDomain(championshipRepository.save(championshipJPAEntity));
    }

    @Override
    public void deleteChampionship(int id) {
        championshipRepository.deleteById(id);
    }

    private CategoryJPAEntity getCategory(Integer categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }

    private Championship toDomain(ChampionshipJPAEntity championship) {
        return new Championship(
                championship.getId(),
                championship.getCategory().getId(),
                championship.getCategory().getName(),
                championship.getYear()
        );
    }
}
