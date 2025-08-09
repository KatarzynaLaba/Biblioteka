package pl.coderslab.biblioteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.biblioteka.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
