package api.ahm.motogp.controller;

import api.ahm.motogp.entities.Category;
import api.ahm.motogp.entities.Championship;
import api.ahm.motogp.repositories.CategoryRepository;
import api.ahm.motogp.repositories.ChampionshipRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/championship")
public class ChampionshipController {

    private final ChampionshipRepository championshipRepository;
    private final CategoryRepository categoryRepository;

    public ChampionshipController(ChampionshipRepository championshipRepository, CategoryRepository categoryRepository) {
        this.championshipRepository = championshipRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public ResponseEntity<List<Championship>> getChampionships(){
        List<Championship> championships = championshipRepository.findAll();
        if(championships.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(championships);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Championship> getChampionship(@PathVariable("id") int id){
        return championshipRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Championship> addChampionship(@RequestBody Championship championship, UriComponentsBuilder ucb) {
        Category category = getCategoryReference(championship.getCategory());
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }

        championship.setId(0);
        championship.setCategory(category);
        Championship newChampionship = championshipRepository.save(championship);
        URI location = ucb.path("/championship/{id}").buildAndExpand(newChampionship.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Championship> deleteChampionship(@PathVariable int id) {
        if (!championshipRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        championshipRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private Category getCategoryReference(Category category) {
        if (category == null || category.getId() == 0 || !categoryRepository.existsById(category.getId())) {
            return null;
        }
        return categoryRepository.getReferenceById(category.getId());
    }
}
