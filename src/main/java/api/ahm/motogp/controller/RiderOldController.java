package api.ahm.motogp.controller;

import api.ahm.motogp.dto.CreateRiderRequestDTO;
import api.ahm.motogp.entities.Country;
import api.ahm.motogp.entities.RiderJPAEntityOld;
import api.ahm.motogp.repositories.CountryRepository;
import api.ahm.motogp.repositories.RiderRepository;
import api.ahm.motogp.services.RiderService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/ridersss")
public class RiderOldController {

    private final RiderRepository riderRepository;
    private final CountryRepository countryRepository;
    private final RiderService riderService;

    public RiderOldController(RiderRepository riderRepository, CountryRepository countryRepository, RiderService riderService) {
        this.riderRepository = riderRepository;
        this.countryRepository = countryRepository;
        this.riderService = riderService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RiderJPAEntityOld> getRiders(Pageable pageable){
        return riderService.getRiders(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RiderJPAEntityOld> getRider(@PathVariable int id){
        return riderService.getRider(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RiderJPAEntityOld addRider(@Valid @RequestBody CreateRiderRequestDTO riderRequest, UriComponentsBuilder ucb){
        /*
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

         */
        return riderService.createRider(riderRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RiderJPAEntityOld> putRider(@PathVariable int id, @RequestBody CreateRiderRequestDTO riderRequest){
        try {
            RiderJPAEntityOld rider = riderRepository.getReferenceById(id);
            Country country = countryRepository.getReferenceById(riderRequest.countryId());
            RiderJPAEntityOld newRider = new RiderJPAEntityOld(id, riderRequest.name(), riderRequest.number(), riderRequest.birthday(), country, rider.isActive());
            riderRepository.save(newRider);
            return ResponseEntity.ok(newRider);
        }catch(EntityExistsException | EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RiderJPAEntityOld> deleteRider(@PathVariable int id){
        try {
            RiderJPAEntityOld rider = riderRepository.getReferenceById(id);
            rider.setActive(false);
            riderRepository.save(rider);
            return ResponseEntity.noContent().build();
        }catch(EntityExistsException | EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

}
