package pro.sku.SQL.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sku.SQL.model.Avatar;
import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findAvatarByStudent_Id(Long studentId);
}