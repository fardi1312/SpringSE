package tn.esprit.massoudfardiexblanc.Services;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.massoudfardiexblanc.DAO.Entities.Bloc;
import tn.esprit.massoudfardiexblanc.DAO.Entities.Foyer;
import tn.esprit.massoudfardiexblanc.DAO.Entities.Universite;
import tn.esprit.massoudfardiexblanc.DAO.Repositories.*;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class ExamenService implements IExamenService {
    UniversiteRepo universiteRepo;
    BlocRepo blocRepo;
    ChambreRepo chambreRepo;
    EtudiantRepo etudiantRepo;
    FoyerRepo foyerRepo;
    ReservationRepo reservationRepo;


    @Override
    public Universite addOrUpdate(Universite u) {
        return universiteRepo.save(u);
    }

    @Override
    public void delete(Universite u) {
        universiteRepo.delete(u);
    }

    @Override
    public void deleteById(long id) {
        universiteRepo.deleteById(id);
    }

    @Override
    public Universite findById(long id) {
        return universiteRepo.findById(id).get();
    }

    @Override
    public java.util.List<Universite> findAll() {
        return universiteRepo.findAll();
    }



    @Override
    public Foyer addOrUpdate(Foyer f) {
        return foyerRepo.save(f);
    }

    @Override
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        Foyer f = foyerRepo.findById(idFoyer).get();
        Universite u = universiteRepo.findByNomUniversite(nomUniversite);
        u.setFoyer(f);
        return universiteRepo.save(u);
    }

//NB: Université est le parent dans l’association entre université et Foyer
    @Override
    public Universite desaffecterFoyerAUniversite(long idUniversite) {
        Universite u = universiteRepo.findById(idUniversite).get();
        u.setFoyer(null);
        return universiteRepo.save(u);
    }


    @Override
    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite) {
        Universite u = universiteRepo.findById(idUniversite).get();
        foyer.setUniversite(u);
        return foyerRepo.save(foyer);
    }


    @Override
    public Foyer ajoutFoyerEtBlocs(Foyer foyer) {
        Foyer f = foyerRepo.save(foyer);
        for (Bloc b : foyer.getBlocs()) {
            b.setFoyer(f);
            blocRepo.save(b);
        }
        return f;
    }


























}
