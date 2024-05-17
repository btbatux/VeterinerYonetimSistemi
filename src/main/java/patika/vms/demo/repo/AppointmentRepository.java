package patika.vms.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import patika.vms.demo.entities.Appointment;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findByAppointmentDateBetweenAndDoctor_Id(LocalDateTime startDate, LocalDateTime endDate, Long doctorId);

    List<Appointment> findByAppointmentDateBetweenAndAnimal_Id(LocalDateTime startDate, LocalDateTime endDate, Long animalId);

    List<Appointment> findByDoctor_IdAndAppointmentDate(int doctorId, LocalDateTime date);

}
