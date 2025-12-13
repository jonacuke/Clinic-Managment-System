package Clinic.Managment.System.Clinic.Managment.System.Repository;

import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
}
