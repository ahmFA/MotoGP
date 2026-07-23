package api.ahm.motogp.controller;

import api.ahm.motogp.entities.Country;
import api.ahm.motogp.entities.GrandPrix;
import api.ahm.motogp.repositories.CountryRepository;
import api.ahm.motogp.repositories.GrandPrixRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/grand-prixes")
public class GrandPrixController {

    private final GrandPrixRepository grandPrixRepository;
    private final CountryRepository countryRepository;

    public GrandPrixController(GrandPrixRepository grandPrixRepository, CountryRepository countryRepository) {
        this.grandPrixRepository = grandPrixRepository;
        this.countryRepository = countryRepository;
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

    @PostMapping
    public ResponseEntity<GrandPrix> addGrandPrix(@RequestBody GrandPrix grandPrix, UriComponentsBuilder ucb) {
        Country country = getCountryReference(grandPrix.getCountry());
        if (country == null) {
            return ResponseEntity.badRequest().build();
        }

        grandPrix.setId(0);
        grandPrix.setCountry(country);
        GrandPrix newGrandPrix = grandPrixRepository.save(grandPrix);
        URI location = ucb.path("/grand-prixes/{id}").buildAndExpand(newGrandPrix.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GrandPrix> putGrandPrix(@PathVariable int id, @RequestBody GrandPrix grandPrix) {
        if (!grandPrixRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Country country = getCountryReference(grandPrix.getCountry());
        if (country == null) {
            return ResponseEntity.badRequest().build();
        }

        grandPrix.setId(id);
        grandPrix.setCountry(country);
        return ResponseEntity.ok(grandPrixRepository.save(grandPrix));
    }

    private Country getCountryReference(Country country) {
        if (country == null || country.getId() == 0 || !countryRepository.existsById(country.getId())) {
            return null;
        }
        return countryRepository.getReferenceById(country.getId());
    }
}
