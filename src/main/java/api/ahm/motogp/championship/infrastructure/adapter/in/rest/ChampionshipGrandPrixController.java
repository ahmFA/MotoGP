package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

import api.ahm.motogp.championship.application.port.in.CreateChampionshipGrandPrixUseCase;
import api.ahm.motogp.championship.application.port.in.DeleteChampionshipGrandPrixUseCase;
import api.ahm.motogp.championship.application.port.in.ListChampionshipGrandPrixUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("championships/{championshipId}/grand-prixes")
public class ChampionshipGrandPrixController {

    private final ListChampionshipGrandPrixUseCase listChampionshipGrandPrixUseCase;
    private final CreateChampionshipGrandPrixUseCase createChampionshipGrandPrixUseCase;
    private final DeleteChampionshipGrandPrixUseCase deleteChampionshipGrandPrixUseCase;

    public ChampionshipGrandPrixController(ListChampionshipGrandPrixUseCase list,
                                           CreateChampionshipGrandPrixUseCase create,
                                           DeleteChampionshipGrandPrixUseCase delete) {
        this.listChampionshipGrandPrixUseCase = list;
        this.createChampionshipGrandPrixUseCase = create;
        this.deleteChampionshipGrandPrixUseCase = delete;
    }

    @GetMapping
    public ResponseEntity<List<ChampionshipGrandPrixResponse>> getChampionshipGrandPrixes(@PathVariable int championshipId) {
        List<ChampionshipGrandPrixResponse> grandPrixes = listChampionshipGrandPrixUseCase.getChampionshipGrandPrixes(championshipId)
                .stream()
                .map(ChampionshipGrandPrixMapper::toResponse)
                .toList();
        return new ResponseEntity<>(grandPrixes, HttpStatus.OK);
    }

    @GetMapping("/{championshipGrandPrixId}")
    public ResponseEntity<ChampionshipGrandPrixResponse> getChampionshipGrandPrix(@PathVariable int championshipId,
                                                                                  @PathVariable int championshipGrandPrixId) {
        ChampionshipGrandPrixResponse grandPrix = ChampionshipGrandPrixMapper.toResponse(
                listChampionshipGrandPrixUseCase.getChampionshipGrandPrix(championshipId, championshipGrandPrixId)
        );
        return new ResponseEntity<>(grandPrix, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ChampionshipGrandPrixResponse> createChampionshipGrandPrix(
            @PathVariable int championshipId,
            @Valid @RequestBody CreateChampionshipGrandPrixRequest createChampionshipGrandPrixRequest) {
        ChampionshipGrandPrixResponse grandPrix = ChampionshipGrandPrixMapper.toResponse(
                createChampionshipGrandPrixUseCase.addChampionshipGrandPrix(
                        ChampionshipGrandPrixMapper.toCommand(createChampionshipGrandPrixRequest, championshipId)
                )
        );
        return new ResponseEntity<>(grandPrix, HttpStatus.CREATED);
    }

    @DeleteMapping("/{championshipGrandPrixId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteChampionshipGrandPrix(@PathVariable int championshipId,
                                                         @PathVariable int championshipGrandPrixId) {
        deleteChampionshipGrandPrixUseCase.deleteChampionshipGrandPrix(championshipId, championshipGrandPrixId);
        return ResponseEntity.noContent().build();
    }
}
