package ch.samt.customertrue.service;

import ch.samt.customertrue.data.MealGroupRepository;
import ch.samt.customertrue.data.ReservationRepository;
import ch.samt.customertrue.domain.MealGroup;
import ch.samt.customertrue.domain.Reservation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealGroupService {

    private final MealGroupRepository mealGroupRepository;

    @Autowired
    public MealGroupService(MealGroupRepository mealGroupRepository) {
        this.mealGroupRepository = mealGroupRepository;
    }

    public List<MealGroup> findAll() {
        return mealGroupRepository.findAll();
    }

    public MealGroup findById(long id) {
        return mealGroupRepository.findMealGroupById(id);
    }
}