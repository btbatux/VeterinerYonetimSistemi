package patika.vms.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import patika.vms.demo.entities.Appointment;
import patika.vms.demo.CustomException.CustomException;
import patika.vms.demo.repo.AppointmentRepository;
import patika.vms.demo.repo.AvailableDateRangeRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AvailableDateRangeRepository availableDateRangeRepository;

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> findById(int id) {
        return appointmentRepository.findById(id);
    }

    public Appointment save(Appointment appointment) {
        LocalDate appointmentDay = appointment.getAppointmentDate().toLocalDate();
        LocalDateTime appointmentDateTime = appointment.getAppointmentDate();

        // Doktorun girilen tarihte müsait olup olmadığını kontrol et
        boolean isAvailable = availableDateRangeRepository.findAll().stream()
                .anyMatch(dateRange ->
                        dateRange.getDoctor().getId() == appointment.getDoctor().getId() &&
                                !appointmentDay.isBefore(dateRange.getStartDate()) &&
                                !appointmentDay.isAfter(dateRange.getEndDate())
                );

        if (!isAvailable) {
            throw new CustomException("Doktor bu tarihte çalışmamaktadır!");
        }

        // Girilen saatte başka bir randevunun olup olmadığını kontrol et
        boolean isTimeSlotOccupied = appointmentRepository
                .findByDoctorIdAndAppointmentDateBetween(appointment.getDoctor().getId(), appointmentDateTime.withMinute(0).withSecond(0).withNano(0), appointmentDateTime.withMinute(59).withSecond(59).withNano(999999999))
                .stream()
                .anyMatch(existingAppointment ->
                        existingAppointment.getAppointmentDate().toLocalTime().equals(appointmentDateTime.toLocalTime())
                );

        if (isTimeSlotOccupied) {
            throw new CustomException("Girilen saatte başka bir randevu mevcuttur.");
        }

        return appointmentRepository.save(appointment);
    }




    public void deleteById(int id) {
        appointmentRepository.deleteById(id);
    }

    public List<Appointment> findByDoctorAndDateRange(int doctorId, LocalDateTime startDate, LocalDateTime endDate) {
        return appointmentRepository.findByDoctorIdAndAppointmentDateBetween(doctorId, startDate, endDate);
    }

    public List<Appointment> findByAnimalAndDateRange(int animalId, LocalDateTime startDate, LocalDateTime endDate) {
        return appointmentRepository.findByAnimalIdAndAppointmentDateBetween(animalId, startDate, endDate);
    }
}
