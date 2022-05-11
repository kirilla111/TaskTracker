package rest;

import hibernate.entity.Desk;
import hibernate.entity.User;
import hibernate.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.security.SecurityService;

import java.util.Set;

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

        return ResponseEntity.ok("Desk created successfully!");
    }

    @PostMapping("/get-all-desks")
    public Set<Desk> getAllDesks(@RequestParam String token) {
        try {
            return securityService.getUserByToken(token).getDesks();
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestParam String token, @RequestParam String newUserLogin, @RequestParam Long deskId) {
        try {
            //securityService.setUser(token, deskService.addUser(securityService.getUserByToken(token), newUserLogin, deskId));
            deskService.addUser(securityService.getUserByToken(token), newUserLogin, deskId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("User saved!");
    }

    @PostMapping("/edit")
    public ResponseEntity<String> editTable(@RequestParam String token, @RequestParam Action action, @RequestBody Desk desk) {
        try {
            User user = securityService.getUserByToken(token);
            switch (action) {
                case UPDATE:
                    deskService.updateDesk(user, desk);
                    break;
                case DELETE:
                    deskService.removeDesk(user, desk);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok("Action complete!");
    }
}
