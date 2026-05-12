package ch.samt.customertrue.data;

import ch.samt.customertrue.domain.Customer;
import ch.samt.customertrue.domain.MealGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealGroupRepository extends JpaRepository<MealGroup, Long> {
    public MealGroup findMealGroupById(Long id);
}
