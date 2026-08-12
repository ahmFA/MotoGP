package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

import api.ahm.motogp.championship.application.port.in.CreateChampionshipTeamUseCase;
import api.ahm.motogp.championship.application.port.in.DeleteChampionshipTeamUseCase;
import api.ahm.motogp.championship.application.port.in.ListChampionshipTeamUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("championships/{championshipId}/teams")
public class ChampionshipTeamController {

    private final ListChampionshipTeamUseCase listChampionshipTeamUseCase;
    private final CreateChampionshipTeamUseCase createChampionshipTeamUseCase;
    private final DeleteChampionshipTeamUseCase deleteChampionshipTeamUseCase;

    public ChampionshipTeamController(ListChampionshipTeamUseCase list,
                                      CreateChampionshipTeamUseCase create,
                                      DeleteChampionshipTeamUseCase delete){
        listChampionshipTeamUseCase = list;
        createChampionshipTeamUseCase = create;
        deleteChampionshipTeamUseCase = delete;
    }

    @GetMapping
    public ResponseEntity<List<ChampionshipTeamResponse>> getChampionshipTeams(@PathVariable int championshipId){
        List<ChampionshipTeamResponse> teams = listChampionshipTeamUseCase.getChampionshipTeams(championshipId).stream().map(ChampionshipTeamMapper::toResponse).toList();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/{championshipTeamId}")
    public ResponseEntity<ChampionshipTeamResponse> getChampionshipTeam(@PathVariable int championshipId, @PathVariable int championshipTeamId){
        ChampionshipTeamResponse teamResponse = ChampionshipTeamMapper.toResponse(listChampionshipTeamUseCase.getChampionshipTeam(championshipId, championshipTeamId));
        return new ResponseEntity<>(teamResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ChampionshipTeamResponse> createChampionshipTeam(@Valid @RequestBody CreateChampionshipTeamRequest createChampionshipTeamRequest){
        ChampionshipTeamResponse teamResponse = ChampionshipTeamMapper.toResponse(createChampionshipTeamUseCase.addChampionshipTeam(ChampionshipTeamMapper.toCommand(createChampionshipTeamRequest)));
        return new ResponseEntity<>(teamResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{championshipTeamId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteChampionshipTeam(@PathVariable int championshipId, @PathVariable int championshipTeamId){
        deleteChampionshipTeamUseCase.deleteChampionshipTeam(championshipId, championshipTeamId);
        return ResponseEntity.noContent().build();
    }
}
