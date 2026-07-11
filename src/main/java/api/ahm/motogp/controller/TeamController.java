package api.ahm.motogp.controller;

import api.ahm.motogp.entities.Team;
import api.ahm.motogp.repositories.TeamRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamRepository teamRepository;


    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping
    public List<Team> getTeams(){
        return teamRepository.findAll();
    }

    @GetMapping("/{id}")
    public Team getTeam(@PathVariable("id") int id){
        return teamRepository.getReferenceById(id);
    }
}
