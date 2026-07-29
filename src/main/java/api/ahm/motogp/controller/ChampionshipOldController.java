package api.ahm.motogp.controller;

import api.ahm.motogp.dto.CreateChampionshipRequest;
import api.ahm.motogp.entities.Category;
import api.ahm.motogp.entities.Championship;
import api.ahm.motogp.repositories.CategoryRepository;
import api.ahm.motogp.repositories.ChampionshipRepository;
import api.ahm.motogp.services.ChampionshipService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/championshipsss")
public class ChampionshipOldController {

    private final ChampionshipRepository championshipRepository;
    private final CategoryRepository categoryRepository;
    private final ChampionshipService championshipService;

    public ChampionshipOldController(ChampionshipRepository championshipRepository,
                                     CategoryRepository categoryRepository,
                                     ChampionshipService championshipService) {
        this.championshipRepository = championshipRepository;
        this.categoryRepository = categoryRepository;
        this.championshipService = championshipService;
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
    public ResponseEntity<?> addChampionship(@RequestBody CreateChampionshipRequest championship, UriComponentsBuilder ucb) {
        Optional<Category> category = categoryRepository.findById(championship.categoryId());
        if(category.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        try {
            Championship champ = new Championship();
            champ.setCategory(category.get());
            champ.setYear(championship.year());
            Championship newChampionship = championshipRepository.save(champ);
            URI location = ucb.path("/championships/{id}").buildAndExpand(newChampionship.getId()).toUri();
            return ResponseEntity.created(location).build();
        }catch(DataIntegrityViolationException dive){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("The unique key already exist");
        }
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
