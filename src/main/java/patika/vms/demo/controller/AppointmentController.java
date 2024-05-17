package patika.vms.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patika.vms.demo.CustomException.CustomException;
import patika.vms.demo.entities.Appointment;
import patika.vms.demo.CustomException.CustomException;
import patika.vms.demo.services.AppointmentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable int id) {
        Optional<Appointment> appointment = appointmentService.findById(id);
        return appointment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody Appointment appointment) {
        try {
            Appointment savedAppointment = appointmentService.save(appointment);
            return ResponseEntity.ok(savedAppointment);
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable int id) {
        appointmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/doctor")
    public List<Appointment> getAppointmentsByDoctorAndDateRange(
            @RequestParam int doctorId,
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return appointmentService.findByDoctorAndDateRange(doctorId, startDate, endDate);
    }

    @GetMapping("/animal")
    public List<Appointment> getAppointmentsByAnimalAndDateRange(
            @RequestParam int animalId,
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return appointmentService.findByAnimalAndDateRange(animalId, startDate, endDate);
    }
}
