package api.ahm.motogp.controller;

import api.ahm.motogp.entities.Country;
import api.ahm.motogp.repositories.CountryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private final CountryRepository countryRepository;

    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @GetMapping
    public ResponseEntity<List<Country>> getCountries(){
        List<Country> countries = countryRepository.findAll();
        if(countries.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountry(@PathVariable int id){
        return countryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Country> addCountry(@RequestBody Country country, UriComponentsBuilder ucb) {
        country.setId(0);
        Country newCountry = countryRepository.save(country);
        URI location = ucb.path("/countries/{id}").buildAndExpand(newCountry.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> putCountry(@PathVariable int id, @RequestBody Country country) {
        if (!countryRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        country.setId(id);
        return ResponseEntity.ok(countryRepository.save(country));
    }
}
