package patika.vms.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import patika.vms.demo.CustomException.DoctorNotAvailableException;
import patika.vms.demo.CustomException.ResourceNotFoundException;
import patika.vms.demo.entities.Appointment;
import patika.vms.demo.entities.AvailableDateRange;
import patika.vms.demo.repo.AppointmentRepository;
import patika.vms.demo.repo.AvailableDateRangeRepository;
import patika.vms.demo.repo.DoctorRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AvailableDateRangeRepository availableDateRangeRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(int id) {
        return appointmentRepository.findById(id);
    }

    public List<Appointment> getAppointmentsByDateBetweenAndDoctorId(LocalDateTime startDate, LocalDateTime endDate, Long doctorId) {
        return appointmentRepository.findByAppointmentDateBetweenAndDoctor_Id(startDate, endDate, doctorId);
    }

    public List<Appointment> getAppointmentsByDateBetweenAndAnimalId(LocalDateTime startDate, LocalDateTime endDate, Long animalId) {
        return appointmentRepository.findByAppointmentDateBetweenAndAnimal_Id(startDate, endDate, animalId);
    }

    public List<Appointment> getAppointmentsByDoctorIdAndDate(int doctorId, LocalDateTime date) {
        return appointmentRepository.findByDoctor_IdAndAppointmentDate(doctorId, date);
    }

    public Appointment saveAppointment(Appointment appointment) {
        Integer doctorId = appointment.getDoctor().getId();
        LocalDateTime appointmentDate = appointment.getAppointmentDate();

        List<AvailableDateRange> availableDateRanges = availableDateRangeRepository.findByDoctor_Id(doctorId);

        boolean isAvailable = availableDateRanges.stream()
                .anyMatch(range -> !appointmentDate.toLocalDate().isBefore(range.getStartDate()) &&
                        !appointmentDate.toLocalDate().isAfter(range.getEndDate()));

        if (!isAvailable) {
            throw new DoctorNotAvailableException("Doctor is not available on this date.");
        }

        return appointmentRepository.save(appointment);
    }

    public AvailableDateRange saveAvailableDateRange(AvailableDateRange availableDateRange) {
        return availableDateRangeRepository.save(availableDateRange);
    }

    public void deleteAppointment(int id) {
        if (!appointmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Appointment with id " + id + " not found.");
        }
        appointmentRepository.deleteById(id);
    }
}
