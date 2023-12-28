package tn.esprit.massoudfardiexblanc.RestControllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.massoudfardiexblanc.DAO.Entities.Universite;
import tn.esprit.massoudfardiexblanc.Services.IExamenService;


import java.util.List;

@RestController
@AllArgsConstructor


public class ExamenRestController {
   IExamenService iExamenService;

   @GetMapping("/examen/findAll")
   public List<Universite> findAll(){
       return iExamenService.findAll();
   }

   @GetMapping("/examen/findById/{id}")
   public Universite findById(@PathVariable long id){
       return iExamenService.findById(id);
   }

   @PostMapping("/examen/add")
   public Universite add(@RequestBody Universite u){
       return iExamenService.addOrUpdate(u);
   }

   @PutMapping("/examen/update")
   public Universite update(@RequestBody Universite u){
       return iExamenService.addOrUpdate(u);
   }

   @DeleteMapping("/examen/delete/{id}")
   public void deleteById(@PathVariable long id){
       iExamenService.deleteById(id);
   }

   @DeleteMapping("/examen/delete")
   public void delete(@RequestBody Universite u){
       iExamenService.delete(u);
   }





}
