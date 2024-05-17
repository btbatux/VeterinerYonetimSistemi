package patika.vms.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import patika.vms.demo.entities.Doctor;
import patika.vms.demo.repo.DoctorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(int id) {
        return doctorRepository.findById(id);
    }

    public List<Doctor> getDoctorsByName(String name) {
        return doctorRepository.findByName(name);
    }

    public List<Doctor> getDoctorsByCity(String city) {
        return doctorRepository.findByCity(city);
    }

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(int id) {
        doctorRepository.deleteById(id);
    }
}
