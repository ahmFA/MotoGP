package api.ahm.motogp.controller;

import api.ahm.motogp.entities.Constructor;
import api.ahm.motogp.entities.Country;
import api.ahm.motogp.repositories.ConstructorRepository;
import api.ahm.motogp.repositories.CountryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/constructors")
public class ConstructorController {

    private final ConstructorRepository constructorRepository;
    private final CountryRepository countryRepository;

    public ConstructorController(ConstructorRepository constructorRepository, CountryRepository countryRepository) {
        this.constructorRepository = constructorRepository;
        this.countryRepository = countryRepository;
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

    @PostMapping
    public ResponseEntity<Constructor> addConstructor(@RequestBody Constructor constructor, UriComponentsBuilder ucb) {
        Country country = getCountryReference(constructor.getCountry());
        if (country == null) {
            return ResponseEntity.badRequest().build();
        }

        constructor.setId(0);
        constructor.setCountry(country);
        Constructor newConstructor = constructorRepository.save(constructor);
        URI location = ucb.path("/constructors/{id}").buildAndExpand(newConstructor.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Constructor> putConstructor(@PathVariable int id, @RequestBody Constructor constructor) {
        if (!constructorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Country country = getCountryReference(constructor.getCountry());
        if (country == null) {
            return ResponseEntity.badRequest().build();
        }

        constructor.setId(id);
        constructor.setCountry(country);
        return ResponseEntity.ok(constructorRepository.save(constructor));
    }

    private Country getCountryReference(Country country) {
        if (country == null || country.getId() == 0 || !countryRepository.existsById(country.getId())) {
            return null;
        }
        return countryRepository.getReferenceById(country.getId());
    }
}
