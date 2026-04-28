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

    // Services for the controller
    private final ExerciseService exerciseService;
    private final RoutineService routineService;
    private final TrainerService trainerService;

    // Constructor for the controller
    public GymController(ExerciseService exerciseService, RoutineService routineService,
            TrainerService trainerService) {
        this.exerciseService = exerciseService;
        this.routineService = routineService;
        this.trainerService = trainerService;
    }

    // Get method to get the index page
    // it uses the exerciseService, routineService and trainerService to get the
    // exercises, routines and trainers
    // to display them on the index page
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("exercises", exerciseService.getAllExercises());
        model.addAttribute("routines", routineService.getAllRoutines());
        model.addAttribute("trainers", trainerService.getAllTrainers());

        return "index";
    }

    // Get method to get the add exercise page
    // it uses the exerciseService to get the exercises to display them on the add
    // exercise page
    @GetMapping("/addExercise")
    public String addExercise() {
        return "addExercise";
    }

    // Get method to get the add routine page
    // it uses the exerciseService and trainerService to get the exercises and
    // trainers
    // to display them on the add routine page
    @GetMapping("/addRoutine")
    public String addRoutinePage(Model model) {
        model.addAttribute("exercises", exerciseService.getAllExercises());
        model.addAttribute("trainers", trainerService.getAllTrainers());
        return "addRoutine";
    }

    // Get method to get the add trainer page
    // it uses the trainerService to get the trainers to display them on the add
    // trainer page
    @GetMapping("/addTrainer")
    public String addTrainerPage() {
        return "addTrainer";
    }

    // Post method to create a new exercise
    // it uses the exerciseService to create the exercise
    @PostMapping("/exercises/new")
    public String createExercise(@ModelAttribute Exercise exercise) {
        exerciseService.createExercise(exercise);
        return "redirect:/#popular-exercises";
    }

    // Post method to create a new routine
    // it uses the routineService to create the routine
    // it also uses the exerciseService and trainerService to get the exercises and
    // trainers
    // to display them on the add routine page
    @PostMapping("/routines/new")
    public String createRoutine(
            @ModelAttribute Routine routine,
            @RequestParam(required = false) List<Long> exerciseIds,
            @RequestParam Long trainerId) {
        routineService.saveRoutineWithIds(routine, exerciseIds, trainerId);
        return "redirect:/#featured-routines";
    }

    // Post method to create a new trainer
    // it uses the trainerService to create the trainer
    @PostMapping("/trainers/new")
    public String createTrainer(@ModelAttribute Trainer trainer) {
        trainerService.createTrainer(trainer);
        return "redirect:/#personal-trainers";
    }

    // Get method to get the edit exercise page
    // it uses the exerciseService to get the exercise by ID
    @GetMapping("/editExercise/{id}")
    public String editExercisePage(@PathVariable Long id, Model model) {
        Exercise exercise = exerciseService.getExerciseById(id);
        if (exercise != null) {
            model.addAttribute("exercise", exercise);
            return "editExercise";
        }
        return "redirect:/";
    }

    // get the page where you can edit a routine
    // it uses the exerciseService and trainerService to get the exercises and
    // trainers
    // for the dropdowns
    @GetMapping("/editRoutine/{id}")
    public String editRoutinePage(@PathVariable Long id, Model model) {
        Routine routine = routineService.getRoutineById(id);

        // Check if the routine exists
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
        // If the routine does not exist, redirect to the home page
        return "redirect:/";
    }

    // get the page where you can edit a trainer
    // it uses the trainerService to get the trainer by ID
    @GetMapping("/editTrainer/{id}")
    public String editTrainerPage(@PathVariable Long id, Model model) {
        Trainer trainer = trainerService.getTrainerById(id);
        if (trainer != null) {
            model.addAttribute("trainer", trainer);
            return "editTrainer";
        }
        // If the trainer does not exist, redirect to the home page
        return "redirect:/";
    }

    // Patch method to update an exercise
    // it uses the exerciseService to update the exercise by ID
    @PatchMapping("/exercises/update")
    public String patchExercise(@RequestParam Long id, @RequestParam Map<String, Object> updates) {

        updates.remove("id");
        updates.remove("_method");
        updates.remove("_csrf");

        updates.values().removeIf(value -> value == null || value.toString().trim().isEmpty());

        exerciseService.patchExercise(id, updates);

        return "redirect:/#popular-exercises";
    }

    // Patch method to update a routine
    // it uses the routineService to update the routine by ID
    @PatchMapping("/routines/update")
    public String patchRoutine(@RequestParam Long id, @RequestParam Map<String, Object> updates,
            @RequestParam(required = false) List<Long> exerciseIds) {

        if (exerciseIds != null) {
            updates.put("exerciseIds", exerciseIds);
        }

        routineService.patchRoutine(id, updates);

        return "redirect:/#featured-routines";
    }

    // Patch method to update a trainer
    // it uses the trainerService to update the trainer by ID
    @PatchMapping("/trainers/update")
    public String patchTrainer(@RequestParam Long id, @RequestParam Map<String, Object> updates) {

        updates.remove("id");
        updates.remove("_method");
        updates.remove("_csrf");

        updates.values().removeIf(value -> value == null || value.toString().trim().isEmpty());

        trainerService.patchTrainer(id, updates);

        return "redirect:/#personal-trainers";
    }

    // Delete method to delete an exercise
    // it uses the exerciseService to delete the exercise by ID
    @GetMapping("/exercises/delete/{id}")
    public String deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return "redirect:/#popular-exercises";
    }

    // Delete method to delete a routine
    // it uses the routineService to delete the routine by ID
    @GetMapping("/routines/delete/{id}")
    public String deleteRoutine(@PathVariable Long id) {
        routineService.deleteRoutine(id);
        return "redirect:/#featured-routines";
    }

    // Delete method to delete a trainer
    // it uses the trainerService to delete the trainer by ID
    @GetMapping("/trainers/delete/{id}")
    public String deleteTrainer(@PathVariable Long id) {
        trainerService.deleteTrainer(id);
        return "redirect:/#personal-trainers";
    }

}