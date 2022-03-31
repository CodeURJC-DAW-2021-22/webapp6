package es.webapp6.padelante.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.webapp6.padelante.model.Match;
import es.webapp6.padelante.model.User;
import es.webapp6.padelante.service.MatchService;
import es.webapp6.padelante.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;


@RestController
@RequestMapping("/api/matches")
public class MatchRestController {

    @Autowired
    private MatchService matchService;

	@Autowired
	private UserService userService;

    @Operation(summary = "Get the match by ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the mathch", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Match.class)) }),
			@ApiResponse(responseCode = "404", description = "Match not found", content = @Content) })
	@JsonView(User.Mostrar.class)
    // Get match by id
    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatch(@PathVariable long id) {

        if(matchService.exist(id)){
            Match match = matchService.findById(id).get();
            return new ResponseEntity<>(match, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "set Match's result")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Resultd updated successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Match.class)) }),
			@ApiResponse(responseCode = "400", description = "Couldn't update the result", content = @Content),
			@ApiResponse(responseCode = "401", description = "You don't have permission to update the result", content = @Content),	
            @ApiResponse(responseCode = "404", description = "Couldn't find the match", content = @Content)		
		})
			
    @PutMapping("/{id}/result")
    public ResponseEntity<Match> resultMatch(HttpServletRequest request,@RequestBody Match updateMatch,@PathVariable long id) {
        Principal principal = request.getUserPrincipal();

        if(matchService.exist(id)){
            if (principal != null) {
                User user = userService.findByName(principal.getName()).get();
                if (matchService.getUserMatches(user).contains(matchService.findById(id).get())) {
                    ArrayList<Integer> result = updateMatch.getResult();
                    boolean cheked = matchService.generateResult(result, matchService.findById(id).get());
                    if (cheked) {
                        return new ResponseEntity<>(matchService.findById(id).get(), HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }
                }else{
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
  
    }
}
