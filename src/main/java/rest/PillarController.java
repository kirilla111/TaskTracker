package rest;

import hibernate.entity.Pillar;
import hibernate.service.PillarSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pillar")
public class PillarController {
    PillarSevice pillarSevice;

    @Autowired
    public PillarController(PillarSevice pillarSevice) {
        this.pillarSevice = pillarSevice;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPillar(@RequestParam String title, @RequestParam Long deskId) {
        try {
            pillarSevice.createPillar(title,deskId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("Desk created successfully!");
    }


}
