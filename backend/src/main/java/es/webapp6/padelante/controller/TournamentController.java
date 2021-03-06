package es.webapp6.padelante.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.webapp6.padelante.model.Match;
import es.webapp6.padelante.model.Team;
import es.webapp6.padelante.model.Tournament;
import es.webapp6.padelante.model.User;
import es.webapp6.padelante.service.MatchService;
import es.webapp6.padelante.service.TeamService;
import es.webapp6.padelante.service.TournamentService;
import es.webapp6.padelante.service.UserService;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

@Controller
public class TournamentController {
    @Autowired
	private TournamentService tournamentService;	

    @Autowired
	private UserService userService;	

	@Autowired
	private MatchService matchService;	

	@Autowired
	private TeamService teamService;

	
	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();
		
		if (principal != null) {

			model.addAttribute("logged", true);
			model.addAttribute("userName", principal.getName());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));
		} else {
			model.addAttribute("logged", false);
		}
	}

    @GetMapping("/create_tournament")
    public String createTournamentPage(Model model,HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();

		if (principal != null) {
			return "create_tournament";
		} else {
			return "login";
		}        
    }

	@PostMapping("/create_tournament")
	public String newTournamentProcess(Model model, Tournament tourna, MultipartFile imageField, HttpServletRequest request) throws IOException {
		if (!imageField.isEmpty()) {
			tourna.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			tourna.setImage(true);
		}

		Principal principal = request.getUserPrincipal();
		String user = principal.getName();
		tourna.setOwner(user);
		tournamentService.save(tourna);
		return "redirect:/";
	}

	@GetMapping("/removeTournament/{id}")
	public String removeTournament(Model model, @PathVariable long id) {

		Optional<Tournament> tourn = tournamentService.findById(id);
		if (tourn.isPresent()) {
			model.addAttribute("removedTournament", tourn.get());
			tournamentService.delete(id);
			
		}
		return "redirect:/admin";
	}

    @GetMapping("/tourns/{id}")
	public String showTournament(Model model, @PathVariable long id, @RequestParam(required = false) Integer page, HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();

		if (tournamentService.exist(id)) {
			Tournament tournament = tournamentService.findById(id).get();
			int numRound = tournament.getRounds();
			boolean r1=false;
			boolean r2=false;
			boolean r3=false;
			boolean r4=false;

			if(numRound==1){
				r1=true;
			}else if(numRound==2){
				r1=true;
				r2=true;
			}else if(numRound==3){
				r1=true;
				r2=true;
				r3=true;
			}else if (numRound==4){
				r1=true;
				r2=true;
				r3=true;
				r4=true;
			}
			
			model.addAttribute("hasr1", r1);
			model.addAttribute("hasr2", r2);
			model.addAttribute("hasr3", r3);
			model.addAttribute("hasr4", r4);

			model.addAttribute("roundFour", matchService.getRoundMatches(tournament,4));
			model.addAttribute("roundTres", matchService.getRoundMatches(tournament,3));
			model.addAttribute("roundTwo",matchService.getRoundMatches(tournament,2));
			model.addAttribute("roundOne",matchService.getRoundMatches(tournament,1));

			model.addAttribute("hasStarted", tournament.isStarted());

			List<Team> teams = tournamentService.getTeamsSignedUp(tournament);
			model.addAttribute("participants", teams);
			if (teams.size()<2){
				model.addAttribute("hasParticipants", false);
			} else {
				model.addAttribute("hasParticipants", true);
			}	

			model.addAttribute("tourns", tournament);

			int pageInt = page == null? 0: page;  
			model.addAttribute("userlist", userService.getUsers(pageInt).getContent());
			model.addAttribute("nextpage", pageInt+1);

			if(principal!=null){
				String userName = principal.getName();
				String ownerTournament=tournament.getOwner();
				Boolean owner = ownerTournament.equals(userName);
				if(owner || userService.findByName(userName).get().getRoles().contains("ADMIN")){
					model.addAttribute("owner", true);
				}else{
					model.addAttribute("owner", false);
				}
				Optional<User> user = userService.findByName(userName);		
				List<Match> matches = matchService.getUserMatches(user.get());

				List<Tournament> userTournaments = tournamentService.getUserTournaments(user.get());
				if(userTournaments.contains(tournament)){
					model.addAttribute("isInTournament", true);
	
				}else{
					model.addAttribute("isInTournament", false);
				}

				model.addAttribute("matches", matches);
				model.addAttribute("numMatches", matches.size());
				model.addAttribute("showMatches", matches.size()>0);
			}else{
				model.addAttribute("owner", false);
			}
			return "tournament";
		} else {
			return "error";
		}
	} 

	@PostMapping("/deleteTourParticipant/{tournid}")
	public String deleteTournParticipant(Model model, @PathVariable long tournid, @RequestParam long teamid){
		Optional<Tournament> tournament = tournamentService.findById(tournid);
		Optional<Team> team = teamService.findById(teamid);

		if(tournament.isPresent() && team.isPresent()){
			tournamentService.deleteParticipant(tournament.get(), team.get());
		}
		return "redirect:/tourns/{tournid}";
	}

	@PostMapping("/update_tourns/{id}")
	public String updateTournament(Model model, @PathVariable long id , @RequestParam String name, @RequestParam String about,
	@RequestParam String ruleset, @RequestParam String location, @RequestParam String inscriptionDate, @RequestParam String startDate, boolean removeImage, 
	MultipartFile imageField) throws IOException, SQLException, ParseException{
		
		Optional<Tournament> tournament = tournamentService.findById(id);

		if (tournament.isPresent()) {
			updateImage(tournament.get(), removeImage, imageField);
			tournament.get().setTournamentName(name);
			tournament.get().setAbout(about);
			tournament.get().setRuleset(ruleset);
			tournament.get().setLocation(location);
			if (!inscriptionDate.equals("")){
				tournament.get().setInscriptionDate(inscriptionDate);
			}
			if (!startDate.equals("")){
				tournament.get().setStartDate(startDate);;
			}
			tournamentService.save(tournament.get());
			return "redirect:/tourns/{id}";
		} else {
			return "error";
		}	
    }

	private void updateImage(Tournament tourn, boolean removeImage, MultipartFile imageField) throws IOException, SQLException {
		
		if (!imageField.isEmpty()) {
			tourn.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			tourn.setImage(true);
		} else {
			if (removeImage) {
				tourn.setImageFile(null);
				tourn.setImage(false);
			} else {
				// Maintain the same image loading it before updating the book
				Tournament dbTournament = tournamentService.findById(tourn.getId()).orElseThrow();
				if (dbTournament.getImage()) {
					tourn.setImageFile(BlobProxy.generateProxy(dbTournament.getImageFile().getBinaryStream(),
						dbTournament.getImageFile().length()));
							tourn.setImage(true);
				}
			}
		}
	}

	@GetMapping("/tourns/{id}/image")
	public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

		Optional<Tournament> tourna = tournamentService.findById(id);
		if (tourna.isPresent() && tourna.get().getImageFile() != null) {

			Resource file = new InputStreamResource(tourna.get().getImageFile().getBinaryStream());

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
					.contentLength(tourna.get().getImageFile().length()).body(file);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/inscription/{idtourn}")
	public String inscriptionTournament (Model model, @PathVariable long idtourn, @RequestParam long id, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		User partner = userService.findById(id).get();

		if (principal != null) {
			User user = userService.findByName(principal.getName()).get();
			Tournament tournament = tournamentService.findById(idtourn).get();
			tournamentService.addParticipant(tournament, teamService.makeTeam(user, partner));
		}
		return "redirect:/tourns/{idtourn}";
	}

	@PostMapping("/startTournament/{tournid}")
	public String startTournament(Model model, @PathVariable long tournid){
		Tournament tournament = tournamentService.findById(tournid).get();
		tournamentService.generateEmptyBracket(tournament);
		tournamentService.assignTeamsStart(tournament);
		tournamentService.setFreeWins(tournament);
		
		return "redirect:/tourns/{tournid}";
	}
}
