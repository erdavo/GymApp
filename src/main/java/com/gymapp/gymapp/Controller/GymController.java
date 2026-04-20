package com.gymapp.gymapp.Controller;

import com.gymapp.gymapp.Model.Exercise;
import com.gymapp.gymapp.Model.Routine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.gymapp.gymapp.Service.ExerciseService;
import com.gymapp.gymapp.Service.RoutineService;
import com.gymapp.gymapp.Service.TrainerService;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.Map;

@Controller
public class GymController {

    private final ExerciseService exerciseService;
    private final RoutineService routineService;
    private final TrainerService trainerService;

    public GymController(ExerciseService exerciseService, RoutineService routineService, TrainerService trainerService) {
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
    public String addRoutinePage() {
        //para la practica 2
        //model.addAttribute("routines", routineService.getAllRoutines());
        return "addRoutine";
    }

    @PostMapping("/exercises/new")
    public String createExercise(@ModelAttribute Exercise exercise) {
        exerciseService.createExercise(exercise);
        return "redirect:/#popular-exercises";
    }

    @PostMapping("/routines/new")
    public String createRoutine(@ModelAttribute Routine routine) {
        routineService.createRoutine(routine);
        return "redirect:/#featured-routines";
    }

    @GetMapping("/editExercise/{id}")
    public String editExercisePage(@PathVariable Integer id, Model model) {
        Exercise exercise = exerciseService.getExerciseById(id);
        if (exercise != null) {
            model.addAttribute("exercise", exercise);
            return "editExercise";
        }
        return "redirect:/";
    }
    
    @GetMapping("/editRoutine/{id}")
    public String editRoutinePage(@PathVariable Integer id, Model model) {
        Routine routine = routineService.getRoutineById(id);
        if (routine != null) {
            model.addAttribute("routine", routine);
            return "editRoutine";
        }
        return "redirect:/";
    }

    @PatchMapping("/exercises/update")
    public String patchExercise(@RequestParam Integer id, @RequestParam Map<String, Object> updates) {

        updates.remove("id");
        updates.remove("_method");
        updates.remove("_csrf");

        updates.values().removeIf(value -> value == null || value.toString().trim().isEmpty());

        exerciseService.patchExercise(id, updates);

        return "redirect:/#popular-exercises";
    }

    @PatchMapping("/routines/update")
    public String patchRoutine(@RequestParam Integer id, @RequestParam Map<String, Object> updates) {

        updates.remove("id");
        updates.remove("_method");
        updates.remove("_csrf");

        updates.values().removeIf(value -> value == null || value.toString().trim().isEmpty());

        routineService.patchRoutine(id, updates);

        return "redirect:/#featured-routines";
    }

    @GetMapping("/exercises/delete/{id}")
    public String deleteExercise(@PathVariable Integer id) {
        exerciseService.deleteExercise(id);
        return "redirect:/#popular-exercises";
    }

    @GetMapping("/routines/delete/{id}")
    public String deleteRoutine(@PathVariable Integer id) {
        routineService.deleteRoutine(id);
        return "redirect:/#featured-routines";
    }

}