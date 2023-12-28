package tn.esprit.massoudfardiexblanc.Services;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.massoudfardiexblanc.DAO.Entities.*;
import tn.esprit.massoudfardiexblanc.DAO.Repositories.*;

import java.time.LocalDate;
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
    public List<Universite> findAll() {
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



    @Override
    public Bloc affecterChambresABloc(List<Long> numChambre, String nomBloc) {
        Bloc b = blocRepo.findByNomBloc(nomBloc);
        for (Long numCh : numChambre) {
            chambreRepo.findById(numCh).ifPresent(c -> {
                c.setBloc(b);
                chambreRepo.save(c);
            });
        }
        return b;
    }



    @Override
    public Bloc affecterBlocAFoyer(String nomBloc, String nomFoyer) {
        Bloc b = blocRepo.findByNomBloc(nomBloc);
        Foyer f = foyerRepo.findByNomFoyer(nomFoyer);
        b.setFoyer(f);
        return blocRepo.save(b);
    }


    @Override
    public List<Chambre> getChambresParNomBloc(String nomBloc) {
        Bloc b = blocRepo.findByNomBloc(nomBloc);
        return b.getChambres();
    }


    @Override
    public long nbChambreParTypeEtBloc(TypeChambre type, long idBloc) {
        return chambreRepo.countByTypeAndBloc(type, blocRepo.findById(idBloc).get());
    }





    @Override
    public void listeChambresParBloc() {
        List<Bloc> blocs = blocRepo.findAll();
        for (Bloc b : blocs) {
            log.info("Bloc " + b.getNomBloc() + " :");
            for (Chambre c : b.getChambres()) {
                log.info(c.toString());
            }
        }
    }


    @Override
    public void pourcentageChambreParTypeChambre() {
  //TypeChambre.SIMPLE DOUBLE  TRIPLE

        List<Bloc> blocs = blocRepo.findAll();
        for (Bloc b : blocs) {
            long nbChambreSimple = chambreRepo.countByTypeAndBloc(TypeChambre.SIMPLE, b);
            long nbChambreDouble = chambreRepo.countByTypeAndBloc(TypeChambre.DOUBLE, b);
            long nbChambreTriple = chambreRepo.countByTypeAndBloc(TypeChambre.TRIPLE, b);
            long nbChambreTotal = b.getChambres().size();
            log.info("Bloc " + b.getNomBloc() + " :");
            log.info("Pourcentage de chambre simple : " + (nbChambreSimple * 100 / nbChambreTotal));
            log.info("Pourcentage de chambre double : " + (nbChambreDouble * 100 / nbChambreTotal));
            log.info("Pourcentage de chambre triple : " + (nbChambreTriple * 100 / nbChambreTotal));
        }
    }






    @Override
    public void nbPlacesDisponibleParChambreAnneeEnCours() {
        // Début "récuperer l'année universitaire actuelle"
        LocalDate dateDebutAU;
        LocalDate dateFinAU;
        int numReservation;
        int year = LocalDate.now().getYear() % 100;
        if (LocalDate.now().getMonthValue() <= 7) {
            dateDebutAU = LocalDate.of(Integer.parseInt("20" + (year - 1)), 9, 15);
            dateFinAU = LocalDate.of(Integer.parseInt("20" + year), 6, 30);
        } else {
            dateDebutAU = LocalDate.of(Integer.parseInt("20" + year), 9, 15);
            dateFinAU = LocalDate.of(Integer.parseInt("20" + (year + 1)), 6, 30);
        }
        // Fin "récuperer l'année universitaire actuelle"
        for (Chambre c : chambreRepo.findAll()) {
            long nbReservation = chambreRepo.countReservationsByIdChambreAndReservationsEstValideAndReservationsAnneeUniversitaireBetween(c.getIdChambre(),true, dateDebutAU, dateFinAU);
            switch (c.getTypeC()) {
                case SIMPLE:
                    if (nbReservation == 0) {
                        log.info("Le nombre de place disponible pour la chambre " + c.getTypeC() + " " + c.getNumeroChambre() + " est 1 ");
                    } else {
                        log.info("La chambre " + c.getTypeC() + " " + c.getNumeroChambre() + " est complete");
                    }
                    break;
                case DOUBLE:
                    if (nbReservation < 2) {
                        log.info("Le nombre de place disponible pour la chambre " + c.getTypeC() + " " + c.getNumeroChambre() + " est " + (2 - nbReservation));
                    } else {
                        log.info("La chambre " + c.getTypeC() + " " + c.getNumeroChambre() + " est complete");
                    }
                    break;
                case TRIPLE:
                    if (nbReservation < 3) {
                        log.info("Le nombre de place disponible pour la chambre " + c.getTypeC() + " " + c.getNumeroChambre() + " est " + (3 - nbReservation));
                    } else {
                        log.info("La chambre " + c.getTypeC() + " " + c.getNumeroChambre() + " est complete");
                    }
            }
        }
    }


}
