package rest;

import hibernate.entity.Card;
import hibernate.service.PillarSevice;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.security.SecurityService;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
    private static String ERROR_MASSAGE = "Error occurred while card request";
    private final PillarSevice pillarSevice;
    private final SecurityService securityService;

    @PostMapping("/create")
    public ResponseEntity<String> createCard(@RequestParam String token,
                                             @RequestParam String title,
                                             @RequestParam(defaultValue = "") String shortDescription,
                                             @RequestParam(defaultValue = "") String description,
                                             @RequestParam Long deskId) {
        try {
            pillarSevice.createCard(securityService.getUserByToken(token), title, shortDescription, description, deskId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ERROR_MASSAGE);
        }

        return ResponseEntity.ok("Card created successfully!");
    }

    @PostMapping("/edit")
    public ResponseEntity<String> editCard(@RequestParam String token, @RequestBody Card cardToEdit) {
        try {
            pillarSevice.editCard(securityService.getUserByToken(token), cardToEdit);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ERROR_MASSAGE);
        }

        return ResponseEntity.ok("Card edited successfully!");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCard(@RequestParam String token, @RequestParam Long cardId){
        try {
            pillarSevice.deleteCard(securityService.getUserByToken(token), cardId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ERROR_MASSAGE);
        }

        return ResponseEntity.ok("Card deleted successfully!");
    }

    @PostMapping("/move")
    public ResponseEntity<String> moveCard(@RequestParam String token, @RequestParam Long pillarId, @RequestParam Long fromPillarId, @RequestParam Long cardId) {
        try {
            pillarSevice.moveCard(securityService.getUserByToken(token), pillarId, fromPillarId, cardId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("Card moved successfully!");
    }
}
