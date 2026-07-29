package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

import api.ahm.motogp.championship.application.port.in.CreateChampionshipUseCase;
import api.ahm.motogp.championship.application.port.in.DeleteChampionshipUseCase;
import api.ahm.motogp.championship.application.port.in.ListChampionshipUseCase;
import api.ahm.motogp.championship.domain.model.Championship;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/championships")
public class ChampionshipController {

    private final ListChampionshipUseCase listChampionshipUseCase;
    private final CreateChampionshipUseCase createChampionshipUseCase;
    private final DeleteChampionshipUseCase deleteChampionshipUseCase;

    public ChampionshipController(ListChampionshipUseCase listChampionshipUseCase,
                                  CreateChampionshipUseCase createChampionshipUseCase,
                                  DeleteChampionshipUseCase deleteChampionshipUseCase) {
        this.listChampionshipUseCase = listChampionshipUseCase;
        this.createChampionshipUseCase = createChampionshipUseCase;
        this.deleteChampionshipUseCase = deleteChampionshipUseCase;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ChampionshipResponse> getChampionships() {
        List<Championship> championships = listChampionshipUseCase.getChampionships();
        return ChampionshipMapper.toResponse(championships);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChampionshipResponse> getChampionship(@PathVariable int id) {
        Optional<ChampionshipResponse> championshipResponse =
                listChampionshipUseCase.getChampionship(id).map(ChampionshipMapper::toResponse);
        return championshipResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ChampionshipResponse> addChampionship(@Valid @RequestBody CreateChampionshipRequest championshipRequest,
                                                               UriComponentsBuilder ucb) {
        Championship championship = createChampionshipUseCase.createChampionship(ChampionshipMapper.toCommand(championshipRequest));
        URI location = ucb.path("/championships/{id}").buildAndExpand(championship.id()).toUri();
        return ResponseEntity.created(location).body(ChampionshipMapper.toResponse(championship));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ChampionshipResponse> deleteChampionship(@PathVariable int id) {
        deleteChampionshipUseCase.deleteChampionship(id);
        return ResponseEntity.noContent().build();
    }
}
