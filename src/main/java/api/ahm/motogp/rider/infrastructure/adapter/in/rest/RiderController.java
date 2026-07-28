package api.ahm.motogp.rider.infrastructure.adapter.in.rest;

import api.ahm.motogp.rider.application.port.in.CreateRiderUseCase;
import api.ahm.motogp.rider.application.port.in.DeleteRiderUseCase;
import api.ahm.motogp.rider.application.port.in.FullUpdateRiderUseCase;
import api.ahm.motogp.rider.application.port.in.ListRiderUseCase;
import api.ahm.motogp.rider.domain.model.Rider;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/riders")
public class RiderController {

    private final ListRiderUseCase listRiderUseCase;
    private final CreateRiderUseCase createRiderUseCase;
    private final FullUpdateRiderUseCase fullUpdateRiderUseCase;
    private final DeleteRiderUseCase deleteRiderUseCase;

    public RiderController(ListRiderUseCase listRiderUseCase,
                           CreateRiderUseCase createRiderUseCase,
                           FullUpdateRiderUseCase fullUpdateRiderUseCase,
                           DeleteRiderUseCase deleteRiderUseCase) {
        this.listRiderUseCase = listRiderUseCase;
        this.createRiderUseCase = createRiderUseCase;
        this.fullUpdateRiderUseCase = fullUpdateRiderUseCase;
        this.deleteRiderUseCase = deleteRiderUseCase;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RiderResponse> getRiders(){
        List<Rider> riders = listRiderUseCase.getRiders();
        return RiderMapper.toResponse(riders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RiderResponse> getRider(@PathVariable int id){
        Optional<RiderResponse> riderResponse = listRiderUseCase.getRider(id).map(RiderMapper::toResponse);
        return riderResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RiderResponse> addRider(@Valid @RequestBody CreateRiderRequest riderRequest, UriComponentsBuilder ucb){
        Rider rider = createRiderUseCase.createRider(RiderMapper.toCommand(riderRequest));
        URI location = ucb.path("/riders/{id}").buildAndExpand(rider.id()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RiderResponse> putRider(@PathVariable int id, @Valid @RequestBody PutRiderRequest riderRequest){
        Rider rider = fullUpdateRiderUseCase.fullUpdateRider(RiderMapper.toCommand(id, riderRequest));
        RiderResponse riderResponse = RiderMapper.toResponse(rider);
        return ResponseEntity.ok(riderResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RiderResponse> deleteRider(@PathVariable int id){
        deleteRiderUseCase.deleteRider(id);
        return ResponseEntity.noContent().build();
    }

}
