package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    // Still using the GET form method, vs. changing to POST b/c of different URL Path used.

    @RequestMapping(value = "results")
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {

       /* ArrayList<HashMap<String, String>> returnedJobs = JobData.findByColumnAndValue(searchType, searchTerm);
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("title", String.valueOf(returnedJobs.size())+" Result(s)");
        model.addAttribute("items", returnedJobs);
        return "search";*/

        if (searchType.toLowerCase().equals("all")) {
            ArrayList<HashMap<String, String>> jobs = JobData.findAll();
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("title", "All Jobs - " + String.valueOf(jobs.size())+" Result(s)");
            model.addAttribute("items", jobs);
            return "search";
        } else {
            ArrayList<HashMap<String, String>> items = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("title", searchType + " - " + String.valueOf(items.size())+" Result(s)");
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("items", items);
            return "search";
        }
    }

}
