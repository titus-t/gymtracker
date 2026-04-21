package backend.gymtracker.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private String exercises;
    private String notes;
    private LocalDate date;
    private boolean isExample;

    @ManyToOne
    @JoinColumn(name = "userid")
    private AppUser appUser;

    public Workout() {}

    public Workout(String name, String category, String exercises, String notes, LocalDate date, boolean isExample, AppUser appUser) {
        super();
        this.name = name;
        this.category = category;
        this.exercises = exercises;
        this.notes = notes;
        this.date = date;
        this.isExample = isExample;
        this.appUser = appUser;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getExercises() { return exercises; }
    public void setExercises(String exercises) { this.exercises = exercises; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public boolean isExample() { return isExample; }
    public void setExample(boolean isExample) { this.isExample = isExample; }

    public AppUser getAppUser() { return appUser; }
    public void setAppUser(AppUser appUser) { this.appUser = appUser; }
}