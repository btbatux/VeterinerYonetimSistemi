package patika.vms.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patika.vms.demo.CustomException.VaccineProtectionException;
import patika.vms.demo.entities.Vaccine;
import patika.vms.demo.services.VaccineService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vaccines")
public class VaccineController {

    @Autowired
    private VaccineService vaccineService;

    // Tüm aşıları getirmek için kullanılan endpoint
    @GetMapping
    public List<Vaccine> getAllVaccines() {
        return vaccineService.getAllVaccines();
    }

    // Belirli bir aşıyı ID'ye göre getirmek için kullanılan endpoint
    @GetMapping("/{id}")
    public ResponseEntity<Vaccine> getVaccineById(@PathVariable int id) {
        Optional<Vaccine> vaccine = vaccineService.getVaccineById(id);
        return vaccine.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Belirli bir hayvana ait aşıları getirmek için kullanılan endpoint
    @GetMapping("/animal/{animalId}")
    public List<Vaccine> getVaccinesByAnimalId(@PathVariable int animalId) {
        return vaccineService.getVaccinesByAnimalId(animalId);
    }

    // Belirli bir tarih aralığında koruma bitiş tarihine sahip aşıları getirmek için kullanılan endpoint
    @GetMapping("/by-date-range")
    public List<Vaccine> getVaccinesByProtectionFinishDateBetween(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return vaccineService.getVaccinesByProtectionFinishDateBetween(startDate, endDate);
    }

    // Yeni bir aşı oluşturmak için kullanılan endpoint
    @PostMapping
    public ResponseEntity<?> createVaccine(@RequestBody Vaccine vaccine) {
        try {
            Vaccine savedVaccine = vaccineService.saveVaccine(vaccine);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedVaccine);
        } catch (VaccineProtectionException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Beklenmedik bir hata oluştu.");
        }
    }

    // Mevcut bir aşıyı güncellemek için kullanılan endpoint
    @PutMapping("/{id}")
    public ResponseEntity<Vaccine> updateVaccine(@PathVariable int id, @RequestBody Vaccine vaccine) {
        Optional<Vaccine> existingVaccine = vaccineService.getVaccineById(id);
        if (existingVaccine.isPresent()) {
            vaccine.setId(id);
            Vaccine updatedVaccine = vaccineService.saveVaccine(vaccine);
            return ResponseEntity.ok(updatedVaccine);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Belirli bir aşıyı silmek için kullanılan endpoint
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVaccine(@PathVariable int id) {
        try {
            vaccineService.deleteVaccine(id);
            return ResponseEntity.ok("Vaccine deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaccine not found");
        }
    }
}
