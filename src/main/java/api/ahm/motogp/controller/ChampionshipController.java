package api.ahm.motogp.controller;

import api.ahm.motogp.entities.Championship;
import api.ahm.motogp.repositories.ChampionshipRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/championship")
public class ChampionshipController {

    private final ChampionshipRepository championshipRepository;

    public ChampionshipController(ChampionshipRepository championshipRepository) {
        this.championshipRepository = championshipRepository;
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

}
