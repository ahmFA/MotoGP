package api.ahm.motogp.controller;

import api.ahm.motogp.dto.CreateRiderRequest;
import api.ahm.motogp.entities.Country;
import api.ahm.motogp.entities.Rider;
import api.ahm.motogp.repositories.CountryRepository;
import api.ahm.motogp.repositories.RiderRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/riders")
public class RiderController {

    private final RiderRepository riderRepository;
    private final CountryRepository countryRepository;

    public RiderController(RiderRepository riderRepository, CountryRepository countryRepository) {
        this.riderRepository = riderRepository;
        this.countryRepository = countryRepository;
    }

    @GetMapping
    public ResponseEntity<List<Rider>> getRiders(){
        List<Rider> riders =  riderRepository.findAll();
        if(riders.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(riders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rider> getRider(@PathVariable int id){
        return riderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Rider> addRider(@RequestBody CreateRiderRequest riderRequest, UriComponentsBuilder ucb){
        if (riderRequest.number() == null || riderRequest.countryId() == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Country country = countryRepository.getReferenceById(riderRequest.countryId());
            Rider rider = new Rider();
            rider.setName(riderRequest.name());
            rider.setNumber(riderRequest.number());
            rider.setBirthday(riderRequest.birthday());
            rider.setCountry(country);

            Rider newRider = riderRepository.save(rider);
            URI location = ucb.path("/riders/{id}").buildAndExpand(newRider.getId()).toUri();
            return ResponseEntity.created(location).build();
        }catch(EntityExistsException | EntityNotFoundException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
