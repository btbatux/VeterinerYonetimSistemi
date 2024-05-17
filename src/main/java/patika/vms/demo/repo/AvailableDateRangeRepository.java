package patika.vms.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import patika.vms.demo.entities.AvailableDateRange;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AvailableDateRangeRepository extends JpaRepository<AvailableDateRange, Integer> {
    List<AvailableDateRange> findByDoctor_Id(int doctorId);


    boolean existsByDoctor_IdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(int doctorId, LocalDate startDate, LocalDate endDate);

}
