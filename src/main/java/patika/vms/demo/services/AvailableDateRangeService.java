package patika.vms.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import patika.vms.demo.entities.AvailableDateRange;
import patika.vms.demo.repo.AvailableDateRangeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AvailableDateRangeService {

    @Autowired
    private AvailableDateRangeRepository availableDateRangeRepository;

    public List<AvailableDateRange> getAllAvailableDateRanges() {
        return availableDateRangeRepository.findAll();
    }

    public Optional<AvailableDateRange> getAvailableDateRangeById(int id) {
        return availableDateRangeRepository.findById(id);
    }

    public List<AvailableDateRange> getAvailableDateRangesByDoctorId(int doctorId) {
        return availableDateRangeRepository.findByDoctor_Id(doctorId);
    }

    public AvailableDateRange saveAvailableDateRange(AvailableDateRange availableDateRange) {
        return availableDateRangeRepository.save(availableDateRange);
    }

    public void deleteAvailableDateRange(Integer id) {
        availableDateRangeRepository.deleteById(id);
    }

    public boolean existsByDoctor_IdAndAvailableDate(int doctorId, LocalDate date) {
        return availableDateRangeRepository.existsByDoctor_IdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(doctorId, date, date);
    }
}
