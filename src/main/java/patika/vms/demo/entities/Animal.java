package patika.vms.demo.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name ="animal")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Animal {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="name",length = 30)
    private String name;

    @Column(name ="species",length = 30)
    private String species;

    @Column(name ="breed")
    private String breed;

    @Column(name ="gender")
    private String gender;

    @Column(name ="colour")
    private String colour;

    @Column(name ="dateOfBirth")
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;


    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments;


    @OneToMany(mappedBy = "animal",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Vaccine>vaccines;



    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", breed='" + breed + '\'' +
                ", gender='" + gender + '\'' +
                ", colour='" + colour + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
