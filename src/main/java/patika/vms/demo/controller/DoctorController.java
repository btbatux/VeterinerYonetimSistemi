package patika.vms.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patika.vms.demo.entities.Animal;
import patika.vms.demo.entities.Doctor;
import patika.vms.demo.services.AnimalService;
import patika.vms.demo.services.DoctorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    /*
     * Bu sınıf, veteriner doktorların kaydedilmesi,
     * güncellenmesi, görüntülenmesi ve
     * silinmesi işlemlerini yapacak.
     * */

    @Autowired
    private DoctorService doctorService;


    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable int id) {
        Optional<Doctor> doctor = doctorService.getDoctorById(id);
        return doctor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/name")
    public List<Doctor> getDoctorsByName(@RequestParam String name) {
        return doctorService.getDoctorsByName(name);
    }

    @GetMapping("/by-city")
    public List<Doctor> getDoctorsByCity(@RequestParam String city) {
        return doctorService.getDoctorsByCity(city);
    }


    @PostMapping  // Genellikle sunucuya yeni bir veri eklerken kullanılır.
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        try {
            Doctor saveDoctor = doctorService.saveDoctor(doctor);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveDoctor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable int id, @RequestBody Doctor doctor) {
        Optional<Doctor> doctorById = doctorService.getDoctorById(id);
        if (doctorById.isPresent()) {
            doctor.setId(id);
            Doctor updatedDoctor = doctorService.saveDoctor(doctor);
            return ResponseEntity.ok(updatedDoctor);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @DeleteMapping("/{id}") //Mevcut bir kaynağı silmek için kullanılır.
    public ResponseEntity<String> deleteDoctor(@PathVariable int id) {
        try {
            doctorService.deleteDoctor(id);
            return ResponseEntity.ok("Doctor deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }
    }
}
