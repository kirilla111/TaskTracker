package rest;

import hibernate.entity.Desk;
import hibernate.entity.Pillar;
import hibernate.entity.User;
import hibernate.service.DeskService;
import hibernate.service.PillarSevice;
import hibernate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.security.SecurityService;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/desk")
public class DeskController {

    private UserService userService;
    private DeskService deskService;
    private SecurityService securityService;
    private PillarSevice pillarSevice;

    @Autowired
    public DeskController(DeskService deskService,
                          SecurityService securityService,
                          UserService userService,
                          PillarSevice pillarSevice) {
        this.securityService = securityService;
        this.deskService = deskService;
        this.userService = userService;
        this.pillarSevice = pillarSevice;
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

    @GetMapping("/get-all-desks")
    public Set<Desk> getAllDesks(@RequestParam String token) {
        try {
            return userService.getUserDesks(securityService.getUserByToken(token));
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestParam String token, @RequestParam String newUserLogin, @RequestParam Long deskId) {
        try {
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
            return ResponseEntity.ok("Error occured!");
        }

        return ResponseEntity.ok("Action complete!");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteTable(@RequestParam String token, @RequestParam Long deskId){
        try {
            deskService.removeDesk(securityService.getUserByToken(token), deskId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Desk deleted!");
    }

    @GetMapping("/get-pillars")
    public Set<Pillar> getAllPillars(@RequestParam String token, @RequestParam Long deskId) {
        try {
            return sorted(deskService.getAllPillars(securityService.getUserByToken(token),deskId));
        } catch (Exception e) {
            return null;
        }
    }

    private Set<Pillar> sorted(Set<Pillar> allPillars) {
        return allPillars.stream().sorted(Comparator.comparing(Pillar::getId)).collect(Collectors.toCollection(LinkedHashSet::new));
    }

}
