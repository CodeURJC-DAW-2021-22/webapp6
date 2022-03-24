package es.webapp6.padelante.controller;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import es.webapp6.padelante.model.Tournament;
import es.webapp6.padelante.service.TournamentService;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentRestController {
	@Autowired
	private TournamentService tournamentService;

	// get all users
	// Only shows 4 of 6 tourns on the 1st page. FIX IT
	@GetMapping("")
	public Page<Tournament> getTournaments(@RequestParam int page) {
		return tournamentService.getTournaments(page);
	}

	// get tournament by id
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Tournament>> getTournament(@PathVariable long id) {

		Optional<Tournament> tournament = tournamentService.findById(id);

		if (tournament != null) {
			return ResponseEntity.ok(tournament);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("")
	public ResponseEntity<Tournament> createTournament(@RequestBody Tournament tournament) {

		tournamentService.save(tournament);

		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(tournament.getId()).toUri();

		return ResponseEntity.created(location).body(tournament);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Tournament> deleteTournament(@PathVariable long id) {

		try {
			tournamentService.delete(id);
			return new ResponseEntity<>(null, HttpStatus.OK);

		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/{id}/image")
	public ResponseEntity<Object> uploadImage(@PathVariable long id, @RequestParam MultipartFile imageFile)
			throws IOException {

		Optional<Tournament> tournament = tournamentService.findById(id);

		if (tournament != null){
			URI location = fromCurrentRequest().build().toUri();

			tournament.get().setImage(true);
			tournament.get().setImageFile(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
			tournamentService.save(tournament.get());

			return ResponseEntity.created(location).build();
		}
		else{
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}/image")
	public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

		Tournament tournament = tournamentService.findById(id).orElseThrow();

		if (tournament.getImageFile() != null) {

			Resource file = new InputStreamResource(tournament.getImageFile().getBinaryStream());

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
					.contentLength(tournament.getImageFile().length()).body(file);

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}/image")
	public ResponseEntity<Object> deleteImage(@PathVariable long id) throws IOException {

		Tournament tournament = tournamentService.findById(id).orElseThrow();

		tournament.setImageFile(null);
		tournament.setImage(false);

		tournamentService.save(tournament);

		return ResponseEntity.noContent().build();
	}
}