package Clinic.Managment.System.Clinic.Managment.System.Service;

import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.Doctor;
import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.Prescription;
import Clinic.Managment.System.Clinic.Managment.System.Repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {
    @Autowired
    private final PrescriptionRepository prescriptionRepository;


    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }


   public Prescription addPrescription(Prescription prescription){
        return prescriptionRepository.save(prescription);
   }

   public Optional<Prescription> findPrescriptionsByPatientId(Long patientId){
       return prescriptionRepository.findById(patientId);
   }

   public Prescription updatePrescription(Long id,Prescription updatePrescription){
        Prescription excisting=prescriptionRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Prescription not found"));
        excisting.setDoza(updatePrescription.getDoza());
        excisting.setMedicament(updatePrescription.getMedicament());
        excisting.setInstructions(updatePrescription.getInstructions());
        return prescriptionRepository.save(excisting);



   }

   public void deletePrescription(Long id){
        if(!prescriptionRepository.existsById(id)){
            throw new RuntimeException("Prescription not found");
        }
        prescriptionRepository.deleteById(id);
   }
}

