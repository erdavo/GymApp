package com.gymapp.gymapp.Config;

import com.gymapp.gymapp.Entities.Exercise;
import com.gymapp.gymapp.Entities.Routine;
import com.gymapp.gymapp.Entities.Trainer;
import com.gymapp.gymapp.Repository.ExerciseRepository;
import com.gymapp.gymapp.Repository.RoutineRepository;
import com.gymapp.gymapp.Repository.TrainerRepository;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Profile("local")
public class MyComponent {

        @Autowired
        private RoutineRepository routineRepository;
        @Autowired
        private TrainerRepository trainerRepository;
        @Autowired
        private ExerciseRepository exerciseRepository;

        @PostConstruct
        public void populateDB() {
                if (trainerRepository.count() == 0 && exerciseRepository.count() == 0
                                && routineRepository.count() == 0) {

                        // 1. Crete and Save Trainers
                        Trainer t1 = new Trainer("Alex Carter", "alex.carter@gymapp.com",
                                        "Madrid, Spain - 28 years old, Strength & Conditioning Specialist",
                                        "/images/alex_carter_trainer.jpg", new ArrayList<>());
                        Trainer t2 = new Trainer("Sofia Martinez", "sofia.martinez@gymapp.com",
                                        "Barcelona, Spain - 32 years old, Functional Training & HIIT Coach",
                                        "/images/sofia_martinez_trainer.jpg", new ArrayList<>());
                        Trainer t3 = new Trainer("Daniel Lopez", "daniel.lopez@gymapp.com",
                                        "Valencia, Spain - 35 years old, Personal Trainer & Nutrition Expert",
                                        "/images/daniel_lopez_trainer.jpg", new ArrayList<>());

                        trainerRepository.save(t1);
                        trainerRepository.save(t2);
                        trainerRepository.save(t3);

                        // 2. Crear y guardar Ejercicios
                        Exercise ex1 = new Exercise("Push Up",
                                        "A bodyweight chest exercise that also works the shoulders and triceps. Keep your body straight and lower yourself under control before pushing back up.",
                                        "Chest", "Medium", "/images/push_up_exercise.jpg");
                        Exercise ex2 = new Exercise("Squat",
                                        "A fundamental lower-body exercise that targets the quadriceps, glutes, and hamstrings. Keep your chest up and lower your hips until your thighs are parallel to the floor.",
                                        "Legs", "Easy", "/images/squat_exercise.jpg");
                        Exercise ex3 = new Exercise("Bench Press",
                                        "A classic strength exercise for building the chest, shoulders, and triceps. Lower the bar with control to the chest and press it back up powerfully.",
                                        "Chest", "Medium", "/images/bench_press_exercise.jpg");
                        Exercise ex4 = new Exercise("Dips",
                                        "A powerful upper-body exercise that mainly targets the chest and triceps. Lower your body slowly and push back up while keeping control of the movement.",
                                        "Chest", "Medium", "/images/dips_exercise.jpg");
                        Exercise ex5 = new Exercise("Pull Up",
                                        "A bodyweight back exercise that develops the lats, upper back, and biceps. Pull yourself up until your chin passes the bar, then lower with control.",
                                        "Back", "Hard", "/images/pull_up_exercise.jpg");
                        Exercise ex6 = new Exercise("Deadlift",
                                        "A compound exercise that strengthens the back, glutes, hamstrings, and core. Lift the weight by driving through the legs while keeping your back straight.",
                                        "Back", "Hard", "/images/deadlift_exercise.jpg");
                        Exercise ex7 = new Exercise("Lunges",
                                        "A lower-body exercise that improves balance and works the quadriceps, glutes, and hamstrings. Step forward, lower your body, and return to the starting position.",
                                        "Legs", "Medium", "/images/lunges_exercise.jpg");
                        Exercise ex8 = new Exercise("Shoulder Press",
                                        "An upper-body exercise focused on the shoulders and triceps. Press the weight overhead in a controlled motion and lower it back down steadily.",
                                        "Arms", "Medium", "/images/shoulder_press_exercise.jpg");
                        Exercise ex9 = new Exercise("Bicep Curl",
                                        "An isolation exercise for the biceps. Curl the weight upward without swinging your body, then lower it slowly to maximize muscle activation.",
                                        "Arms", "Easy", "/images/bicep_curl_exercise.jpg");
                        Exercise ex10 = new Exercise("Tricep Extension",
                                        "An isolation exercise that targets the triceps. Extend the arms fully and return to the starting position with control for proper muscle engagement.",
                                        "Arms", "Easy", "/images/tricep_extension_exercise.jpg");
                        Exercise ex11 = new Exercise("Plank",
                                        "A core stability exercise that strengthens the abs, lower back, and shoulders. Hold your body in a straight line while keeping the core engaged.",
                                        "Abs", "Easy", "/images/plank_exercise.jpg");
                        Exercise ex12 = new Exercise("Crunch",
                                        "A basic abdominal exercise that focuses on the upper abs. Lift your shoulders off the floor using your core and lower back down slowly.",
                                        "Abs", "Easy", "/images/crunch_exercise.jpg");
                        Exercise ex13 = new Exercise("Lat Pulldown",
                                        "A machine-based back exercise that targets the latissimus dorsi and upper back. Pull the bar down toward your upper chest and return it slowly.",
                                        "Back", "Medium", "/images/lat_pulldown_exercise.jpg");
                        Exercise ex14 = new Exercise("Leg Press",
                                        "A machine exercise for building leg strength, especially in the quadriceps and glutes. Push the platform away in a controlled movement without locking the knees.",
                                        "Legs", "Medium", "/images/leg_press_exercise.jpg");
                        Exercise ex15 = new Exercise("Romanian Deadlift",
                                        "A lower-body and posterior-chain exercise that emphasizes the hamstrings and glutes. Hinge at the hips while keeping the back straight and the weight close to the body.",
                                        "Legs", "Hard", "/images/romanian_deadlift_exercise.jpg");
                        Exercise ex16 = new Exercise("Lateral Raise",
                                        "A shoulder isolation exercise that targets the side delts. Raise the weights to shoulder height with slight control and lower them slowly.",
                                        "Arms", "Easy", "/images/lateral_raise_exercise.jpg");
                        Exercise ex17 = new Exercise("Barbell Row",
                                        "A compound back exercise that builds thickness in the upper and middle back. Pull the bar toward your torso while maintaining a stable bent-over position.",
                                        "Back", "Hard", "/images/barbell_row_exercise.jpg");
                        Exercise ex18 = new Exercise("Treadmill Running",
                                        "A cardio exercise that helps improve endurance, stamina, and cardiovascular health. Maintain a steady pace or vary the intensity with intervals.",
                                        "Cardio", "Medium", "/images/treadmill_running_exercise.jpg");

                        exerciseRepository.save(ex1);
                        exerciseRepository.save(ex2);
                        exerciseRepository.save(ex3);
                        exerciseRepository.save(ex4);
                        exerciseRepository.save(ex5);
                        exerciseRepository.save(ex6);
                        exerciseRepository.save(ex7);
                        exerciseRepository.save(ex8);
                        exerciseRepository.save(ex9);
                        exerciseRepository.save(ex10);
                        exerciseRepository.save(ex11);
                        exerciseRepository.save(ex12);
                        exerciseRepository.save(ex13);
                        exerciseRepository.save(ex14);
                        exerciseRepository.save(ex15);
                        exerciseRepository.save(ex16);
                        exerciseRepository.save(ex17);
                        exerciseRepository.save(ex18);

                        // 3. Crear Rutinas, asignarles ejercicios y entrenadores
                        Routine r1 = new Routine("PPL",
                                        "A Push Pull Legs routine designed to build muscle and improve overall strength with a balanced weekly split.",
                                        "Medium", "/images/ppl_routine.jpg", new ArrayList<>(), t1);
                        r1.getExercises().add(ex3);
                        r1.getExercises().add(ex5);
                        r1.getExercises().add(ex2);
                        r1.getExercises().add(ex8);

                        Routine r2 = new Routine("Full Body",
                                        "A full body routine ideal for beginners who want to train all major muscle groups in a simple and effective way.",
                                        "Easy", "/images/full_body_routine.jpg", new ArrayList<>(), t2);
                        r2.getExercises().add(ex3);
                        r2.getExercises().add(ex17);
                        r2.getExercises().add(ex14);
                        r2.getExercises().add(ex15);

                        Routine r3 = new Routine("Upper-Lower",
                                        "An upper lower split focused on building strength and muscle by dividing workouts between upper-body and lower-body days.",
                                        "Hard", "/images/upper_lower_routine.jpg", new ArrayList<>(), t3);
                        r3.getExercises().add(ex1);
                        r3.getExercises().add(ex2);
                        r3.getExercises().add(ex13);
                        r3.getExercises().add(ex11);

                        routineRepository.save(r1);
                        routineRepository.save(r2);
                        routineRepository.save(r3);
                }
        }
}