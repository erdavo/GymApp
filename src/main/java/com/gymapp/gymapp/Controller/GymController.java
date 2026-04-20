package com.gymapp.gymapp.Controller;

import com.gymapp.gymapp.Model.Exercise;
import com.gymapp.gymapp.Model.Routine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gymapp.gymapp.Service.ExerciseService;
import com.gymapp.gymapp.Service.RoutineService;
import com.gymapp.gymapp.Service.TrainerService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.spec.RSAOtherPrimeInfo;

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

    @PostMapping("/exercises/update")
    public String updateExercise(@ModelAttribute Exercise exercise) {
        exerciseService.updateExercise(exercise.getId(), exercise);
        return "redirect:/#popular-exercises";
    }

    @PostMapping("/routines/update")
    public String updateRoutine(@ModelAttribute Routine routine) {
        routineService.updateRoutine(routine.getId(), routine);
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