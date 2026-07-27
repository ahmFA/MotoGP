package api.ahm.motogp.controller;

import api.ahm.motogp.dto.PatchChampionshipRiderRequest;
import api.ahm.motogp.entities.Championship;
import api.ahm.motogp.entities.ChampionshipRider;
import api.ahm.motogp.entities.ChampionshipTeam;
import api.ahm.motogp.entities.Rider;
import api.ahm.motogp.repositories.ChampionshipRepository;
import api.ahm.motogp.repositories.ChampionshipRiderRepository;
import api.ahm.motogp.repositories.ChampionshipTeamRepository;
import api.ahm.motogp.repositories.RiderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/championships/{championshipId}/riders")
public class ChampionshipRiderController {

    private final ChampionshipRiderRepository championshipRiderRepository;
    private final ChampionshipRepository championshipRepository;
    private final ChampionshipTeamRepository championshipTeamRepository;
    private final RiderRepository riderRepository;

    public ChampionshipRiderController(ChampionshipRiderRepository championshipRiderRepository,
                                       ChampionshipRepository championshipRepository,
                                       ChampionshipTeamRepository championshipTeamRepository,
                                       RiderRepository riderRepository) {
        this.championshipRiderRepository = championshipRiderRepository;
        this.championshipRepository = championshipRepository;
        this.championshipTeamRepository = championshipTeamRepository;
        this.riderRepository = riderRepository;
    }

    @GetMapping
    public ResponseEntity<List<ChampionshipRider>> getChampionshipRiders(@PathVariable int championshipId) {
        List<ChampionshipRider> riders = championshipRiderRepository.findByChampionshipId(championshipId);

        if (riders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(riders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChampionshipRider> getChampionshipRider(@PathVariable int championshipId, @PathVariable int id) {
        return championshipRiderRepository.findByChampionshipIdAndId(championshipId, id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ChampionshipRider> addChampionshipRider(@PathVariable int championshipId,
                                                                 @RequestBody ChampionshipRider championshipRider,
                                                                 UriComponentsBuilder ucb) {
        Championship championship = getChampionshipReference(championshipId);
        Rider rider = getRiderReference(championshipRider.getRider());
        ChampionshipTeam team = getChampionshipTeamReference(championshipId, championshipRider.getTeam());
        if (championship == null || rider == null || team == null) {
            return ResponseEntity.badRequest().build();
        }

        championshipRider.setId(0);
        championshipRider.setChampionship(championship);
        championshipRider.setRider(rider);
        championshipRider.setTeam(team);
        ChampionshipRider newChampionshipRider = championshipRiderRepository.save(championshipRider);
        URI location = ucb.path("/championships/{championshipId}/riders/{id}")
                .buildAndExpand(championshipId, newChampionshipRider.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ChampionshipRider> patchChampionshipRider(@PathVariable int championshipId,
                                                                   @PathVariable int id,
                                                                   @RequestBody PatchChampionshipRiderRequest request) {
        Optional<ChampionshipRider> championshipRider = championshipRiderRepository.findByChampionshipIdAndId(championshipId, id);
        if (championshipRider.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ChampionshipRider updatedChampionshipRider = championshipRider.get();
        if (request.teamId() != null) {
            Optional<ChampionshipTeam> team = championshipTeamRepository.findByChampionshipIdAndId(championshipId, request.teamId());
            if (team.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            updatedChampionshipRider.setTeam(team.get());
        }
        if (request.number() != null) {
            updatedChampionshipRider.setNumber(request.number());
        }

        return ResponseEntity.ok(championshipRiderRepository.save(updatedChampionshipRider));
    }

    private Championship getChampionshipReference(int championshipId) {
        if (!championshipRepository.existsById(championshipId)) {
            return null;
        }
        return championshipRepository.getReferenceById(championshipId);
    }

    private Rider getRiderReference(Rider rider) {
        if (rider == null || rider.getId() == 0 || !riderRepository.existsById(rider.getId())) {
            return null;
        }
        return riderRepository.getReferenceById(rider.getId());
    }

    private ChampionshipTeam getChampionshipTeamReference(int championshipId, ChampionshipTeam team) {
        if (team == null || team.getId() == 0) {
            return null;
        }
        return championshipTeamRepository.findByChampionshipIdAndId(championshipId, team.getId()).orElse(null);
    }
}
