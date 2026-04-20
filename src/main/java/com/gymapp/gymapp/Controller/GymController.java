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


}