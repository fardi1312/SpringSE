package tn.esprit.massoudfardiexblanc.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_FOYER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Foyer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idFoyer;
    String nomFoyer;
    long capaciteFoyer;

    //ONE TO ONE UNIVERSITE fOYER
    @OneToOne(mappedBy = "foyer")
    @JsonIgnore

    Universite universite;

    //ONE TO MANY   Bloc fOYER bi-directional
    @OneToMany(mappedBy = "foyer")
    @JsonIgnore

    List<Bloc> blocs = new ArrayList<>();




}
