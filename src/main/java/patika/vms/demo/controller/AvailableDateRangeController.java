package patika.vms.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patika.vms.demo.entities.AvailableDateRange;
import patika.vms.demo.services.AvailableDateRangeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/available-date-ranges")
public class AvailableDateRangeController {

    @Autowired
    private AvailableDateRangeService availableDateRangeService;

    @GetMapping
    public List<AvailableDateRange> getAllAvailableDateRanges() {
        return availableDateRangeService.getAllAvailableDateRanges();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvailableDateRange> getAvailableDateRangeById(@PathVariable Integer id) {
        Optional<AvailableDateRange> availableDateRange = availableDateRangeService.getAvailableDateRangeById(id);
        return availableDateRange.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/by-doctor/{doctorId}")
    public List<AvailableDateRange> getAvailableDateRangesByDoctorId(@PathVariable int doctorId) {
        return availableDateRangeService.getAvailableDateRangesByDoctorId(doctorId);
    }

    @PostMapping
    public ResponseEntity<AvailableDateRange> createAvailableDateRange(@RequestBody AvailableDateRange availableDateRange) {
        try {
            AvailableDateRange savedAvailableDateRange = availableDateRangeService.saveAvailableDateRange(availableDateRange);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAvailableDateRange);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvailableDateRange> updateAvailableDateRange(@PathVariable int id, @RequestBody AvailableDateRange availableDateRange) {
        Optional<AvailableDateRange> existingAvailableDateRange = availableDateRangeService.getAvailableDateRangeById(id);
        if (existingAvailableDateRange.isPresent()) {
            availableDateRange.setId(id);
            AvailableDateRange updatedAvailableDateRange = availableDateRangeService.saveAvailableDateRange(availableDateRange);
            return ResponseEntity.ok(updatedAvailableDateRange);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAvailableDateRange(@PathVariable int id) {
        try {
            availableDateRangeService.deleteAvailableDateRange(id);
            return ResponseEntity.ok("Available date range deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Available date range not found");
        }
    }
}
