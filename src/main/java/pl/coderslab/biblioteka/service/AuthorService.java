package pl.coderslab.biblioteka.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.biblioteka.model.Author;
import pl.coderslab.biblioteka.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

    public Author updateAuthor(Long id, Author updateAuthor) {
                return authorRepository.findById(id)
                .map(author -> {
                    author.setFirstName(updateAuthor.getFirstName());
                    author.setLastName(updateAuthor.getLastName());
                    author.setBio(updateAuthor.getBio());
                    return authorRepository.save(author);
                })
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }
}
