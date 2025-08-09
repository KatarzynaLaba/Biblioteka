package pl.coderslab.biblioteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.biblioteka.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
