package api.ahm.motogp.controller;

import api.ahm.motogp.entities.Rider;
import api.ahm.motogp.repositories.RiderRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/riders")
public class RiderController {

    private final RiderRepository riderRepository;

    public RiderController(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    @GetMapping
    public List<Rider> getRiders(){
        return riderRepository.findAll();
    }

    @GetMapping("/{id}")
    public Rider getRider(@PathVariable("id") int id){
        return riderRepository.getReferenceById(id);
    }
}
