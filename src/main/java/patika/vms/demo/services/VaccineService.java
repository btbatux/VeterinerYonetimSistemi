package patika.vms.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import patika.vms.demo.CustomException.ResourceNotFoundException;
import patika.vms.demo.CustomException.VaccineProtectionException;
import patika.vms.demo.entities.Vaccine;
import patika.vms.demo.repo.VaccineRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VaccineService {

    @Autowired
    private VaccineRepository vaccineRepository;

    public List<Vaccine> getAllVaccines() {
        return vaccineRepository.findAll();
    }

    public Optional<Vaccine> getVaccineById(int id) {
        return vaccineRepository.findById(id);
    }

    public List<Vaccine> getVaccinesByAnimalId(int animalId) {
        return vaccineRepository.findByAnimal_Id(animalId);
    }

    public List<Vaccine> getVaccinesByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate) {
        return vaccineRepository.findByProtectionFinishDateBetween(startDate, endDate);
    }

    public Vaccine saveVaccine(Vaccine vaccine) {
        // Aynı aşıdan ve koruyuculuk süresi bitmemiş bir aşı var mı kontrol et
        List<Vaccine> existingVaccines = vaccineRepository.findByAnimal_IdAndNameAndCode(
                vaccine.getAnimal().getId(),
                vaccine.getName(),
                vaccine.getCode()
        );

        for (Vaccine existingVaccine : existingVaccines) {
            if (existingVaccine.getProtectionFinishDate().isAfter(LocalDate.now())) {
                throw new VaccineProtectionException("Aynı aşıdan zaten yapıldı. " +
                        "Koruma süresi henüz sona ermedi.");
            }
        }

        return vaccineRepository.save(vaccine);
    }

    public void deleteVaccine(int id) {
        if (!vaccineRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vaccine with id " + id + " not found.");
        }
        vaccineRepository.deleteById(id);
    }
}
