package patika.vms.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patika.vms.demo.CustomException.DoctorNotAvailableException;
import patika.vms.demo.entities.Appointment;
import patika.vms.demo.services.AppointmentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // Tüm randevuları getirmek için kullanılan endpoint
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    // Belirli bir randevuyu ID'ye göre getirmek için kullanılan endpoint
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable int id) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        return appointment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Yeni bir randevu oluşturmak için kullanılan endpoint
    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody Appointment appointment) {
        try {
            Appointment savedAppointment = appointmentService.saveAppointment(appointment);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAppointment);
        } catch (DoctorNotAvailableException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Beklenmedik bir hata oluştu.");
        }
    }

    // Mevcut bir randevuyu güncellemek için kullanılan endpoint
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable int id, @RequestBody Appointment appointment) {
        Optional<Appointment> existingAppointment = appointmentService.getAppointmentById(id);
        if (existingAppointment.isPresent()) {
            appointment.setId(id);
            Appointment updatedAppointment = appointmentService.saveAppointment(appointment);
            return ResponseEntity.ok(updatedAppointment);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Belirli bir randevuyu silmek için kullanılan endpoint
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable int id) {
        try {
            appointmentService.deleteAppointment(id);
            return ResponseEntity.ok("Randevu başarıyla silindi");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Randevu bulunamadı");
        }
    }
}
