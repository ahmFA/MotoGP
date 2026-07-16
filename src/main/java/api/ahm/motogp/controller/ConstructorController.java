package api.ahm.motogp.controller;

import api.ahm.motogp.entities.Constructor;
import api.ahm.motogp.repositories.ConstructorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/constructors")
public class ConstructorController {

    private final ConstructorRepository constructorRepository;

    public ConstructorController(ConstructorRepository constructorRepository) {
        this.constructorRepository = constructorRepository;
    }

    @GetMapping
    public ResponseEntity<List<Constructor>> getConstructors(){
        List<Constructor> constructors = constructorRepository.findAll();
        if(constructors.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(constructors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Constructor> getConstructor(@PathVariable("id") int id){
        return constructorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
