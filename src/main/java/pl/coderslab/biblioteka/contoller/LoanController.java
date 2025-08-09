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

    @PostMapping("/borrow")
    public ResponseEntity<Loan> borrowBook(@RequestParam Long userId, @RequestParam Long bookId) {
        return ResponseEntity.ok(loanService.loanBookToUser(userId, bookId));
    }

    @PostMapping("/return/{loanId}")
    public ResponseEntity<Loan> returnBook(@PathVariable Long loanId) {
        return ResponseEntity.ok(loanService.returnBook(loanId));
    }

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }
}
