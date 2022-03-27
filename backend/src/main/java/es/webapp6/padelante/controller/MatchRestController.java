package es.webapp6.padelante.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.webapp6.padelante.model.Match;
import es.webapp6.padelante.service.MatchService;


@RestController
@RequestMapping("/api/matches")
public class MatchRestController {

    @Autowired
    private MatchService matchService;


    @GetMapping("/{id}")
    public ResponseEntity<Optional<Match>> getUser(@PathVariable long id) {

        Optional<Match> match = matchService.findById(id);
        

        if (match.get() != null) {
            return ResponseEntity.ok(match);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/result")
    public ResponseEntity<Object> resultMatch(HttpServletRequest request,@RequestBody Match updateMatch,@PathVariable long id) {
        Principal principal = request.getUserPrincipal();
       
        Optional<Match> match = matchService.findById(id);

        if(match!=null){
            boolean hasa1 = match.get().getTeamOne().getUserA().getName().equals(principal.getName());
            boolean hasa2 = match.get().getTeamOne().getUserB().getName().equals(principal.getName());
            boolean hasa3 = match.get().getTeamTwo().getUserA().getName().equals(principal.getName());
            boolean hasa4 = match.get().getTeamTwo().getUserB().getName().equals(principal.getName());
            boolean has = hasa1 || hasa2 || hasa3 || hasa4;
            if(has){
                match.get().setResult(updateMatch.getResult());
                matchService.save(match.get());
                return new ResponseEntity<>(match, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
           
         
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
  
    }



    
}
