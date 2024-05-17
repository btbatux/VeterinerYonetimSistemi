package patika.vms.demo.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name ="customer")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name",length = 35)
    private String name;

    @Column(name = "phone",length = 35)
    private String phone;

    @Column(name = "mail",length = 45)
    private String mail;

    @Column(name = "address",length = 100)
    private String address;

    @Column(name = "city",length = 100)
    private String city;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Animal> animals;



}
