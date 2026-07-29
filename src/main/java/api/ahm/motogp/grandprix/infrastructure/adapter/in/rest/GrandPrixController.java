package api.ahm.motogp.grandprix.infrastructure.adapter.in.rest;

import api.ahm.motogp.grandprix.application.port.in.CreateGrandPrixUseCase;
import api.ahm.motogp.grandprix.application.port.in.FullUpdateGrandPrixUseCase;
import api.ahm.motogp.grandprix.application.port.in.ListGrandPrixUseCase;
import api.ahm.motogp.grandprix.domain.model.GrandPrix;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grand-prixes")
public class GrandPrixController {

    private final ListGrandPrixUseCase listGrandPrixUseCase;
    private final CreateGrandPrixUseCase createGrandPrixUseCase;
    private final FullUpdateGrandPrixUseCase fullUpdateGrandPrixUseCase;

    public GrandPrixController(ListGrandPrixUseCase listGrandPrixUseCase,
                               CreateGrandPrixUseCase createGrandPrixUseCase,
                               FullUpdateGrandPrixUseCase fullUpdateGrandPrixUseCase) {
        this.listGrandPrixUseCase = listGrandPrixUseCase;
        this.createGrandPrixUseCase = createGrandPrixUseCase;
        this.fullUpdateGrandPrixUseCase = fullUpdateGrandPrixUseCase;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GrandPrixResponse> getGrandPrixes() {
        List<GrandPrix> grandPrixes = listGrandPrixUseCase.getGrandPrixes();
        return GrandPrixMapper.toResponse(grandPrixes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrandPrixResponse> getGrandPrix(@PathVariable int id) {
        Optional<GrandPrixResponse> grandPrixResponse = listGrandPrixUseCase.getGrandPrix(id).map(GrandPrixMapper::toResponse);
        return grandPrixResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GrandPrixResponse> addGrandPrix(@Valid @RequestBody CreateGrandPrixRequest grandPrixRequest,
                                                          UriComponentsBuilder ucb) {
        GrandPrix grandPrix = createGrandPrixUseCase.createGrandPrix(GrandPrixMapper.toCommand(grandPrixRequest));
        URI location = ucb.path("/grand-prixes/{id}").buildAndExpand(grandPrix.id()).toUri();
        return ResponseEntity.created(location).body(GrandPrixMapper.toResponse(grandPrix));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GrandPrixResponse> putGrandPrix(@PathVariable int id,
                                                          @Valid @RequestBody PutGrandPrixRequest grandPrixRequest) {
        GrandPrix grandPrix = fullUpdateGrandPrixUseCase.fullUpdateGrandPrix(GrandPrixMapper.toCommand(id, grandPrixRequest));
        return ResponseEntity.ok(GrandPrixMapper.toResponse(grandPrix));
    }
}
