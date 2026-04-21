package backend.gymtracker.web;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import backend.gymtracker.domain.Workout;
import backend.gymtracker.domain.WorkoutRepository;

@RestController
public class WorkoutRestController {

    private final WorkoutRepository repository;

    public WorkoutRestController(WorkoutRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/workouts")
    public List<Workout> workoutListRest() {
        return (List<Workout>) repository.findAll();
    }
}