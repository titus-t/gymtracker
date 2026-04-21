package backend.gymtracker.web;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import backend.gymtracker.domain.AppUser;
import backend.gymtracker.domain.AppUserRepository;
import backend.gymtracker.domain.Workout;
import backend.gymtracker.domain.WorkoutRepository;

@Controller
public class WorkoutController {

    private final WorkoutRepository workoutRepository;
    private final AppUserRepository appUserRepository;

    public WorkoutController(WorkoutRepository workoutRepository, AppUserRepository appUserRepository) {
        this.workoutRepository = workoutRepository;
        this.appUserRepository = appUserRepository;
    }

    private void addDataToModel(Model model, Authentication auth, String category) {
    if (category == null) {
        model.addAttribute("examples", workoutRepository.findByIsExampleTrue());
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            model.addAttribute("myWorkouts", workoutRepository.findByAppUserUsernameAndIsExampleFalseOrderByCategoryAsc(auth.getName()));
        }
    } else {
        model.addAttribute("examples", workoutRepository.findByCategoryAndIsExampleTrue(category));
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            model.addAttribute("myWorkouts", workoutRepository.findByCategoryAndAppUserUsernameAndIsExampleFalse(category, auth.getName()));
        }
    }
}

    @GetMapping("/")
    public String showIndex(Model model, Authentication auth) {
        addDataToModel(model, auth, null);
        model.addAttribute("isFrontPage", true);
        return "index";
    }

    @GetMapping("/ylakroppa")
    public String showYlakroppa(Model model, Authentication auth) {
        addDataToModel(model, auth, "Yläkroppa");
        return "index";
    }

    @GetMapping("/jalat")
    public String showJalat(Model model, Authentication auth) {
        addDataToModel(model, auth, "Jalat");
        return "index";
    }

    @GetMapping("/cardio")
    public String showCardio(Model model, Authentication auth) {
        addDataToModel(model, auth, "Cardio");
        return "index";
    }

    @GetMapping("/omat-treenit")
public String showMyWorkouts(Model model, Authentication auth) {
    if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
        model.addAttribute("myWorkouts", workoutRepository.findByAppUserUsernameAndIsExampleFalseOrderByCategoryAsc(auth.getName()));
        model.addAttribute("isOnlyMyWorkouts", true);
        return "index";
    }
    return "redirect:/login";
}

    @RequestMapping(value = "/add")
    public String addWorkout(Model model) {
        model.addAttribute("workout", new Workout());
        return "addworkout";
    }

    @PostMapping("/save")
public String save(Workout workout, Authentication auth) {
    AppUser currentUser = appUserRepository.findByUsername(auth.getName());
    boolean isAdmin = auth.getAuthorities().stream()
        .anyMatch(a -> a.getAuthority().equals("ADMIN"));

    if (!isAdmin) {
        workout.setExample(false);
    }
    
    workout.setAppUser(currentUser);

    if (workout.getDate() == null) {
        workout.setDate(java.time.LocalDate.now());
    }
    
    workoutRepository.save(workout);
    return "redirect:/";
}

    @GetMapping("/delete/{id}")
public String deleteWorkout(@PathVariable("id") Long workoutId, Authentication auth) {
    Workout workout = workoutRepository.findById(workoutId).get();
    boolean isAdmin = auth.getAuthorities().stream()
        .anyMatch(a -> a.getAuthority().equals("ADMIN"));
    
    if (isAdmin || (workout.getAppUser() != null && workout.getAppUser().getUsername().equals(auth.getName()))) {
        workoutRepository.deleteById(workoutId);
    }
    return "redirect:/";
}

    @GetMapping("/edit/{id}")
    public String editWorkout(@PathVariable("id") Long workoutId, Model model, Authentication auth) {
        Workout workout = workoutRepository.findById(workoutId).get();
        if (workout.getAppUser().getUsername().equals(auth.getName())) {
            model.addAttribute("workout", workout);
            return "editworkout";
        }
        return "redirect:/";
    }

     @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
}