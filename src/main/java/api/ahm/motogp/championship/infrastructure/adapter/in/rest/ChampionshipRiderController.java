package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

import api.ahm.motogp.championship.application.port.in.CreateChampionshipRiderUseCase;
import api.ahm.motogp.championship.application.port.in.DeleteChampionshipRiderUseCase;
import api.ahm.motogp.championship.application.port.in.ListChampionshipRiderUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("championships/{championshipId}/riders")
public class ChampionshipRiderController {

    private final ListChampionshipRiderUseCase listChampionshipRiderUseCase;
    private final CreateChampionshipRiderUseCase createChampionshipRiderUseCase;
    private final DeleteChampionshipRiderUseCase deleteChampionshipRiderUseCase;

    public ChampionshipRiderController(ListChampionshipRiderUseCase list,
                                       CreateChampionshipRiderUseCase create,
                                       DeleteChampionshipRiderUseCase delete){
        this.listChampionshipRiderUseCase = list;
        createChampionshipRiderUseCase = create;
        deleteChampionshipRiderUseCase = delete;
    }

    @GetMapping
    public ResponseEntity<List<ChampionshipRiderResponse>> getChampionshipRiders(@PathVariable int championshipId) {
        List<ChampionshipRiderResponse> riders = listChampionshipRiderUseCase.getChampionshipRiders(championshipId)
                .stream()
                .map(ChampionshipRiderMapper::toResponse)
                .toList();
        return new ResponseEntity<>(riders, HttpStatus.OK);
    }

    @GetMapping("/{championshipRiderId}")
    public ResponseEntity<ChampionshipRiderResponse> getChampionshipRider(@PathVariable int championshipId,
                                                                          @PathVariable int championshipRiderId) {
        ChampionshipRiderResponse rider = ChampionshipRiderMapper.toResponse(
                listChampionshipRiderUseCase.getChampionshipRider(championshipId, championshipRiderId)
        );
        return new ResponseEntity<>(rider, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ChampionshipRiderResponse> createChampionshipRider(
            @PathVariable int championshipId,
            @Valid @RequestBody CreateChampionshipRiderRequest createChampionshipRiderRequest) {
        ChampionshipRiderResponse rider = ChampionshipRiderMapper.toResponse(
                createChampionshipRiderUseCase.addChampionshipRider(
                        ChampionshipRiderMapper.toCommand(createChampionshipRiderRequest, championshipId)
                )
        );
        return new ResponseEntity<>(rider, HttpStatus.CREATED);
    }

    @DeleteMapping("/{championshipRiderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteChampionshipRider(@PathVariable int championshipId,
                                                     @PathVariable int championshipRiderId) {
        deleteChampionshipRiderUseCase.deleteChampionshipRider(championshipId, championshipRiderId);
        return ResponseEntity.noContent().build();
    }
}
