package pl.coderslab.biblioteka.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.biblioteka.model.Loan;
import pl.coderslab.biblioteka.service.LoanService;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @GetMapping
    public List<Loan> getAllLoans() {
        List<Loan> loans = loanService.getAllLoans();
        System.out.println("Loans: " + loans);
        return loans;
    }

    @PostMapping("/loan")
    public ResponseEntity<Loan> loanBookToUser(@RequestParam Long userId, @RequestParam Long bookId) {
        try {
            Loan loan = loanService.loanBookToUser(userId, bookId);
            return ResponseEntity.ok(loan);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/return/{loanId}")
    public ResponseEntity<Loan> returnBook(@PathVariable Long loanId) {
        try {
            Loan returnedLoan = loanService.returnBook(loanId);
            return ResponseEntity.ok(returnedLoan);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable Long id, @RequestBody Loan loan) {
        try {
            Loan updatedLoan = loanService.updateLoan(id, loan);
            return ResponseEntity.ok(updatedLoan);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
