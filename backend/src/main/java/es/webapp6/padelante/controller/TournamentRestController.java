package es.webapp6.padelante.controller;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.annotation.JsonView;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import es.webapp6.padelante.model.Match;
import es.webapp6.padelante.model.Team;
import es.webapp6.padelante.model.Tournament;
import es.webapp6.padelante.model.User;
import es.webapp6.padelante.service.MatchService;
import es.webapp6.padelante.service.TeamService;
import es.webapp6.padelante.service.TournamentService;
import es.webapp6.padelante.service.UserService;

import org.springframework.data.domain.Page;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentRestController {
	@Autowired
	private TournamentService tournamentService;

	@Autowired
    private MatchService matchService;

	@Autowired
	private UserService userService;

	@Autowired
	private TeamService teamService;

	// get all users
	@GetMapping("")
	public ResponseEntity<Page<Tournament>> getTournaments(@RequestParam int page) {
		return new ResponseEntity<>(tournamentService.getTournaments(page), HttpStatus.OK);
	}

	// get tournament by id
	@GetMapping("/{id}")
	public ResponseEntity<Tournament> getTournament(@PathVariable long id) {

		if (tournamentService.exist(id)) {
			Tournament tournament = tournamentService.findById(id).get();
			return new ResponseEntity<>(tournament, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@JsonView(User.Mostrar.class)
	@GetMapping("/{id}/teams")
	public ResponseEntity<List<Team>> getTournamentTeams(@PathVariable long id) {

		if (tournamentService.exist(id)) {
			Tournament tournament = tournamentService.findById(id).get();
			return new ResponseEntity<>(tournamentService.getTeamsSignedUp(tournament), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@JsonView(User.Mostrar.class)
	@GetMapping("/{id}/matches")
    public ResponseEntity<List<Match>> getRound(@PathVariable long id, @RequestParam Integer round) {
        
        if (tournamentService.exist(id)) {
            Tournament tournament = tournamentService.findById(id).get();
            if(round > 0 && tournament.getRounds() >= round){ 
                List<Match> rMatches = matchService.getRoundMatches(tournament, round);
				return new ResponseEntity<>(rMatches, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }        
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

	@PostMapping("")
	public ResponseEntity<Tournament> createTournament(@RequestBody Tournament tournament, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();

		if (principal != null) {
			// Data that must be default because tournament could be inconsistent
			tournament.setOwner(principal.getName());
			tournament.setNumParticipants(0);
			tournament.setNumSignedUp(0);
			tournament.setRounds(0);

			tournamentService.save(tournament);

			URI location = fromCurrentRequest().path("/{id}").buildAndExpand(tournament.getId()).toUri();

			return ResponseEntity.created(location).body(tournament);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Tournament> deleteTournament(@PathVariable long id, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();

		if (tournamentService.exist(id)) {
			if (principal != null && principal.getName().equals("admin")) {
				tournamentService.delete(id);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{id}/teams")
	@JsonView(User.Mostrar.class)
	public ResponseEntity<List<Team>> tournamentTeams (@PathVariable long id, @RequestBody User user2, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();

		if (tournamentService.exist(id) && userService.exist(user2.getId())) {
			Tournament tournament = tournamentService.findById(id).get();
			User partner = userService.findById(user2.getId()).get();

			if (principal != null) {
				User user = userService.findByName(principal.getName()).get();

				int response = tournamentService.addParticipant(tournament, teamService.makeTeam(user, partner));
				if (response == 0) {
					return new ResponseEntity<>(tournamentService.getTeamsSignedUp(tournament), HttpStatus.OK);
				} else {
					return new ResponseEntity<>(tournamentService.getTeamsSignedUp(tournament), HttpStatus.BAD_REQUEST);
				}
			}else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}/teams/{teamid}")
	public ResponseEntity<List<Team>> deleteTournamentTeam(@PathVariable long id, @PathVariable long teamid,
		HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();

		if (tournamentService.exist(id) && teamService.exist(teamid)) {
			Tournament tournament = tournamentService.findById(id).get();
			Team team = teamService.findById(teamid).get();
			if (principal != null && principal.getName().equals(tournament.getOwner())) {
				tournamentService.deleteParticipant(tournament, team);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<Tournament> updateTournament(@PathVariable long id, @RequestBody Tournament tournament,
			HttpServletRequest request) throws SQLException {

		Principal principal = request.getUserPrincipal();

		if (tournamentService.exist(id)) {
			Tournament bdTournament = tournamentService.findById(id).get();
			if (principal != null && principal.getName().equals(bdTournament.getOwner())) {
				if (tournament.isStarted()) {
					if (bdTournament.getNumSignedUp()>1) {
						tournamentService.generateEmptyBracket(bdTournament);
						tournamentService.assignTeamsStart(bdTournament);
						tournamentService.setFreeWins(bdTournament);
						return new ResponseEntity<>(tournamentService.findById(id).get(), HttpStatus.OK);
					} else {
						return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
					}
				} else {
					// Data that must not change because tournament could be inconsistent
					tournament.setId(id);
					tournament.setOwner(bdTournament.getOwner());
					tournament.setNumParticipants(bdTournament.getNumParticipants());
					tournament.setNumSignedUp(bdTournament.getNumSignedUp());
					tournament.setRounds(bdTournament.getRounds());
					if (bdTournament.getImage()) {
						tournament.setImageFile(BlobProxy.generateProxy(bdTournament.getImageFile().getBinaryStream(),
								bdTournament.getImageFile().length()));
						tournament.setImage(true);
					}
					tournamentService.save(tournament);
					return new ResponseEntity<>(tournament, HttpStatus.OK);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/{id}/image")
	public ResponseEntity<Object> uploadImage(@PathVariable long id, @RequestParam MultipartFile imageFile,
			HttpServletRequest request)
			throws IOException {

		Principal principal = request.getUserPrincipal();

		if (tournamentService.exist(id)) {
			Tournament tournament = tournamentService.findById(id).get();
			if (principal != null && principal.getName().equals(tournament.getOwner())) {
				URI location = fromCurrentRequest().build().toUri();

				tournament.setImage(true);
				tournament.setImageFile(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
				tournamentService.save(tournament);

				return ResponseEntity.created(location).build();
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/image")
	public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

		if (tournamentService.exist(id)) {
			Tournament tournament = tournamentService.findById(id).get();

			if (tournament.getImage()) {
				Resource file = new InputStreamResource(tournament.getImageFile().getBinaryStream());

				return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
						.contentLength(tournament.getImageFile().length()).body(file);

			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/{id}/image")
	public ResponseEntity<Object> deleteImage(@PathVariable long id, HttpServletRequest request) throws IOException {
		Principal principal = request.getUserPrincipal();

		if (tournamentService.exist(id)) {
            Tournament tournament = tournamentService.findById(id).get();
            if (principal != null && principal.getName().equals(tournament.getOwner())) {
                if (tournament.getImage()) {
                    tournament.setImageFile(null);
                    tournament.setImage(false);

                    tournamentService.save(tournament);

                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}