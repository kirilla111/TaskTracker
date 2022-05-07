package rest;

import hibernate.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.security.SecurityService;

@RestController
@RequestMapping("/desk")
public class DeskController {

    private DeskService deskService;
    private SecurityService securityService;

    @Autowired
    public DeskController(DeskService deskService, SecurityService securityService) {
        this.securityService = securityService;
        this.deskService = deskService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTable(@RequestParam String title, @RequestParam String description, @RequestParam String token) {
        try {
            deskService.createDesk(title, description, securityService.getUserByToken(token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("User registered successfully!");
    }
}
