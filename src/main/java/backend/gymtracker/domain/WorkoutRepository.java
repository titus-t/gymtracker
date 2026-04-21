package backend.gymtracker.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface WorkoutRepository extends CrudRepository<Workout, Long> {
    
    List<Workout> findByIsExampleTrue();
    
    List<Workout> findByCategoryAndIsExampleTrue(String category);
    
    List<Workout> findByAppUserUsernameAndIsExampleFalse(String username);
    
    List<Workout> findByCategoryAndAppUserUsernameAndIsExampleFalse(String category, String username);

    List<Workout> findByAppUserUsernameAndIsExampleFalseOrderByCategoryAsc(String username);
}