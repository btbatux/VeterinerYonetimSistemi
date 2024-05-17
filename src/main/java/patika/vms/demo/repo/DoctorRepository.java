package patika.vms.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import patika.vms.demo.entities.Doctor;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    List<Doctor> findByName(String name);

    List<Doctor> findByCity(String city);

    // Ekstra sorgular gerekirse buraya


}
