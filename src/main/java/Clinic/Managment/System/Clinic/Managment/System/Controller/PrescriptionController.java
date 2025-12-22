package Clinic.Managment.System.Clinic.Managment.System.Controller;

import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.Prescription;
import Clinic.Managment.System.Clinic.Managment.System.Service.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }


    @GetMapping("/patient/{patientId}")

    public ResponseEntity<Prescription> getPrescriptionByPatientId(@PathVariable Long id) {
        return prescriptionService.findPrescriptionsByPatientId(id)
                .map(prescription -> ResponseEntity.ok(prescription))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/prescription")
    public ResponseEntity<Prescription> addPrescription(@RequestBody Prescription prescription) {
        Prescription savePrescription = prescriptionService.addPrescription(prescription);
        return ResponseEntity.status(HttpStatus.CREATED).body(savePrescription);
    }

    @PutMapping("/prescription/{id}")
    public ResponseEntity<Prescription> updatePrescription(@PathVariable Long id, @RequestBody Prescription prescription1) {
        return prescriptionService.findPrescriptionsByPatientId(id)
                .map(prescription -> {
                    prescription.setDoza(prescription1.getDoza());
                    prescription.setInstructions(prescription.getInstructions());
                    prescription.setMedicament(prescription1.getMedicament());
                    Prescription updatePrescription = prescriptionService.addPrescription(prescription);
                    return ResponseEntity.ok(updatePrescription);


                })
                .orElseGet(() -> ResponseEntity.notFound().build());


    }


    @DeleteMapping("/prescriptions/{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable Long id) {
        try {
            prescriptionService.deletePrescription(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }


    }
}
