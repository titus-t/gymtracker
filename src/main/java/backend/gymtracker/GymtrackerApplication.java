package backend.gymtracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.gymtracker.domain.AppUser;
import backend.gymtracker.domain.Workout;
import backend.gymtracker.domain.AppUserRepository;
import backend.gymtracker.domain.WorkoutRepository;

import java.time.LocalDate;

@SpringBootApplication
public class GymtrackerApplication {

    private static final Logger log = LoggerFactory.getLogger(GymtrackerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GymtrackerApplication.class, args);
    }

    @Bean
    public CommandLineRunner gymtrackerDemo(
        WorkoutRepository workoutRepository,
        AppUserRepository userRepository) {

        return (args) -> {
            AppUser user1 = new AppUser("titus", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "titus@gymtracker.com", "USER");
            AppUser admin = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin@gymtracker.com", "ADMIN");
            
            userRepository.save(user1);
            userRepository.save(admin);

            workoutRepository.save(new Workout("Perus Jalkatreeni", "Jalat", "Kyykky, Prässi, Pohkeet", "Hyvä perusohjelma aloittelijalle", LocalDate.now(), true, admin));
            workoutRepository.save(new Workout("Yläkropan voima", "Yläkroppa", "Penkki, Leuanveto, Pystypunnerrus", "Keskity puhtaisiin toistoihin", LocalDate.now(), true, admin));
            workoutRepository.save(new Workout("Palauttava lenkki", "Cardio", "Juoksumatto tai pyörä", "Syke alle 130", LocalDate.now(), true, admin));

            workoutRepository.save(new Workout("Oma jalkarääkki", "Jalat", "Kyykky 3x10, SJMV 3x12", "Tuntui hyvältä, ensi kerralla lisää painoa", LocalDate.now(), false, user1));
        };
    }
}