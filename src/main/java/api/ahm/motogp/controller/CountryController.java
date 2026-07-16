package api.ahm.motogp.controller;

import api.ahm.motogp.entities.Country;
import api.ahm.motogp.repositories.CountryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
