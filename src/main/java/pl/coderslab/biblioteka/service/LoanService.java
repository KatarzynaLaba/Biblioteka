package pl.coderslab.biblioteka.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.biblioteka.model.Book;
import pl.coderslab.biblioteka.model.Loan;
import pl.coderslab.biblioteka.model.User;
import pl.coderslab.biblioteka.repository.BookRepository;
import pl.coderslab.biblioteka.repository.LoanRepository;
import pl.coderslab.biblioteka.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan loanBookToUser(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("No copies available");
        }

        // zmniejszamy liczbę dostępnych egzemplarzy
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        Loan loan = Loan.builder()
                .user(user)
                .book(book)
                .loanDate(LocalDate.now())
                .dueDate(LocalDate.now().plusWeeks(2))
                .build();

        return loanRepository.save(loan);
    }

    public Loan returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        if (loan.getReturnDate() != null) {
            throw new RuntimeException("Book already returned");
        }

        // zwracamy książkę do puli dostępnych egzemplarzy
        Book book = loan.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        loan.setReturnDate(LocalDate.now());
        return loanRepository.save(loan);
    }

    public Loan updateLoan(Long id, Loan loanDetails) {
        Loan existing = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        existing.setBook(loanDetails.getBook());
        existing.setUser(loanDetails.getUser());
        existing.setLoanDate(loanDetails.getLoanDate());
        existing.setReturnDate(loanDetails.getReturnDate());

        return loanRepository.save(existing);
    }
}

