package api.ahm.motogp.controller;

import api.ahm.motogp.entities.GrandPrix;
import api.ahm.motogp.repositories.GrandPrixRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/grand-prixes")
public class GrandPrixController {

    private final GrandPrixRepository grandPrixRepository;

    public GrandPrixController(GrandPrixRepository grandPrixRepository) {
        this.grandPrixRepository = grandPrixRepository;
    }

    @GetMapping
    public ResponseEntity<List<GrandPrix>> getGrandPrixes(){
        List<GrandPrix> grandPrixesList = grandPrixRepository.findAll();
        if(grandPrixesList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(grandPrixesList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrandPrix> getGrandPrix(@PathVariable int id){
        return grandPrixRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
