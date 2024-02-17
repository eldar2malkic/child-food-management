package org.example.controllers;

import org.example.data.GoalRepository;
import org.example.models.Goals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GoalsController {

    @Autowired
    private GoalRepository goalRepository;
    @GetMapping("/goal-form")
    public String showGoalForm(Model model) {
        model.addAttribute("goal", new Goals());
        return "goal-form";
    }

    @PostMapping("/submit-goal")
    public String submitGoalForm(@ModelAttribute Goals goal) {
        goalRepository.save(goal);
        // Handle form submission here will eventually connect to SQL database
        return "redirect:/goals";
    }
    @GetMapping("/goals")
    public String listGoals(Model model) {
        List<Goals> goals = (List<Goals>) goalRepository.findAll();
        model.addAttribute("goals", goals);
        return "goals"; // Need to have a goals.html template to display the list of goals
    }

}