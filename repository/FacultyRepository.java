package pro.sku.SQL.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sku.SQL.model.Faculty;

import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Optional<Faculty> getByNameIgnoreCaseOrColorIgnoreCase(String name, String color);

}
