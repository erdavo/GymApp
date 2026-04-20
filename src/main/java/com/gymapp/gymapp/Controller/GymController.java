package com.gymapp.gymapp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gymapp.gymapp.Service.ExerciseService;
import com.gymapp.gymapp.Service.RoutineService;

@Controller
public class GymController {

    private final ExerciseService exerciseService;
    private final RoutineService routineService;
    // TODO: private final TrainerService trainerService;

    // TODO: add TrainerService trainerService in constructor
    public GymController(ExerciseService exerciseService, RoutineService routineService) {
        this.exerciseService = exerciseService;
        this.routineService = routineService;
        // TODO: add this.trainerService = trainerService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("exercises", exerciseService.getAllExercises());
        model.addAttribute("routines", routineService.getAllRoutines());
        // TODO: model.addAttribute("trainers", trainerService.getAllTrainers());

        return "index";
    }

    @GetMapping("/addExercise")
    public String addExercise() {
        return "addExercise"; 
    }
}