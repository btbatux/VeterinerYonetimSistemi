package patika.vms.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointment")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "appointment_date")
    private LocalDateTime appointmentDate;


    @ManyToOne
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;



    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", appointmentDate=" + appointmentDate +
                ", animal=" + animal +
                ", doctor=" + doctor +
                '}';
    }
}
