package com.gymapp.gymapp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.gymapp.gymapp.Entities.Exercise;
import com.gymapp.gymapp.Entities.Routine;
import com.gymapp.gymapp.Entities.Trainer;
import com.gymapp.gymapp.Service.ExerciseService;
import com.gymapp.gymapp.Service.RoutineService;
import com.gymapp.gymapp.Service.TrainerService;

import java.util.List;
import java.util.Map;

@Controller
public class GymController {

    private final ExerciseService exerciseService;
    private final RoutineService routineService;
    private final TrainerService trainerService;

    public GymController(ExerciseService exerciseService, RoutineService routineService,
            TrainerService trainerService) {
        this.exerciseService = exerciseService;
        this.routineService = routineService;
        this.trainerService = trainerService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("exercises", exerciseService.getAllExercises());
        model.addAttribute("routines", routineService.getAllRoutines());
        model.addAttribute("trainers", trainerService.getAllTrainers());

        return "index";
    }

    @GetMapping("/addExercise")
    public String addExercise() {
        return "addExercise";
    }

    @GetMapping("/addRoutine")
    public String addRoutinePage(Model model) {
        model.addAttribute("exercises", exerciseService.getAllExercises());
        model.addAttribute("trainers", trainerService.getAllTrainers());
        return "addRoutine";
    }

    @GetMapping("/addTrainer")
    public String addTrainerPage() {
        return "addTrainer";
    }

    @PostMapping("/exercises/new")
    public String createExercise(@ModelAttribute Exercise exercise) {
        exerciseService.createExercise(exercise);
        return "redirect:/#popular-exercises";
    }

    @PostMapping("/routines/new")
    public String createRoutine(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String difficulty,
            @RequestParam(required = false) List<Long> exerciseIds,
            @RequestParam Long trainerId) {
        Routine routine = new Routine();

        routine.setName(name);
        routine.setDescription(description);
        routine.setDifficulty(difficulty);

        List<Exercise> selectedExercises = exerciseService.getAllExercises()
                .stream()
                .filter(exercise -> exerciseIds != null && exerciseIds.contains(exercise.getId()))
                .toList();

        routine.setExercises(selectedExercises);

        Trainer selectedTrainer = trainerService.getTrainerById(trainerId);
        routine.setTrainer(selectedTrainer);

        routineService.createRoutine(routine);

        return "redirect:/#featured-routines";
    }

    @PostMapping("/trainers/new")
    public String createTrainer(@ModelAttribute Trainer trainer) {
        trainerService.createTrainer(trainer);
        return "redirect:/#personal-trainers";
    }

    @GetMapping("/editExercise/{id}")
    public String editExercisePage(@PathVariable Long id, Model model) {
        Exercise exercise = exerciseService.getExerciseById(id);
        if (exercise != null) {
            model.addAttribute("exercise", exercise);
            return "editExercise";
        }
        return "redirect:/";
    }

    @GetMapping("/editRoutine/{id}")
    public String editRoutinePage(@PathVariable Long id, Model model) {
        Routine routine = routineService.getRoutineById(id);

        if (routine != null) {
            List<Map<String, Object>> exercisesWithSelected = exerciseService.getAllExercises()
                    .stream()
                    .map(exercise -> Map.<String, Object>of(
                            "id", exercise.getId(),
                            "name", exercise.getName(),
                            "muscleGroup", exercise.getMuscleGroup(),
                            "difficulty", exercise.getDifficulty(),
                            "imageUrl", exercise.getImageUrl(),
                            "selected", routine.getExercises()
                                    .stream()
                                    .anyMatch(selectedExercise -> selectedExercise.getId().equals(exercise.getId()))))
                    .toList();

            List<Map<String, Object>> trainersWithSelected = trainerService.getAllTrainers()
                    .stream()
                    .map(trainer -> Map.<String, Object>of(
                            "id", trainer.getId(),
                            "name", trainer.getName(),
                            "email", trainer.getEmail(),
                            "selected", routine.getTrainer() != null &&
                                    routine.getTrainer().getId().equals(trainer.getId())))
                    .toList();

            model.addAttribute("routine", routine);
            model.addAttribute("exercises", exercisesWithSelected);
            model.addAttribute("trainers", trainersWithSelected);

            return "editRoutine";
        }

        return "redirect:/";
    }

    @GetMapping("/editTrainer/{id}")
    public String editTrainerPage(@PathVariable Long id, Model model) {
        Trainer trainer = trainerService.getTrainerById(id);
        if (trainer != null) {
            model.addAttribute("trainer", trainer);
            return "editTrainer";
        }
        return "redirect:/";
    }

    @PatchMapping("/exercises/update")
    public String patchExercise(@RequestParam Long id, @RequestParam Map<String, Object> updates) {

        updates.remove("id");
        updates.remove("_method");
        updates.remove("_csrf");

        updates.values().removeIf(value -> value == null || value.toString().trim().isEmpty());

        exerciseService.patchExercise(id, updates);

        return "redirect:/#popular-exercises";
    }

    @PatchMapping("/routines/update")
    public String patchRoutine(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String difficulty,
            @RequestParam(required = false) List<Long> exerciseIds,
            @RequestParam Long trainerId) {

        Routine routine = routineService.getRoutineById(id);

        if (routine != null) {
            routine.setName(name);
            routine.setDescription(description);
            routine.setDifficulty(difficulty);

            // IMPORTANTE: lista mutable (no usar toList())
            List<Exercise> selectedExercises = exerciseService.getAllExercises()
                    .stream()
                    .filter(exercise -> exerciseIds != null && exerciseIds.contains(exercise.getId()))
                    .collect(java.util.stream.Collectors.toCollection(java.util.ArrayList::new));

            routine.setExercises(selectedExercises);

            Trainer selectedTrainer = trainerService.getTrainerById(trainerId);
            routine.setTrainer(selectedTrainer);

            routineService.updateRoutine(id, routine);
        }

        return "redirect:/#featured-routines";
    }

    @PatchMapping("/trainers/update")
    public String patchTrainer(@RequestParam Long id, @RequestParam Map<String, Object> updates) {

        updates.remove("id");
        updates.remove("_method");
        updates.remove("_csrf");

        updates.values().removeIf(value -> value == null || value.toString().trim().isEmpty());

        trainerService.patchTrainer(id, updates);

        return "redirect:/#personal-trainers";
    }

    @GetMapping("/exercises/delete/{id}")
    public String deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return "redirect:/#popular-exercises";
    }

    @GetMapping("/routines/delete/{id}")
    public String deleteRoutine(@PathVariable Long id) {
        routineService.deleteRoutine(id);
        return "redirect:/#featured-routines";
    }

    @GetMapping("/trainers/delete/{id}")
    public String deleteTrainer(@PathVariable Long id) {
        trainerService.deleteTrainer(id);
        return "redirect:/#personal-trainers";
    }

}