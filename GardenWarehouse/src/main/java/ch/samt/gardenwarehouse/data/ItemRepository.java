package ch.samt.gardenwarehouse.data;

import ch.samt.gardenwarehouse.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByCodeIgnoreCase(String code);
}
