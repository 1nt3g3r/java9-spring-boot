package com.first.boot.springboot.controller;

import com.first.boot.springboot.service.HtmlPageService;
import com.first.boot.springboot.service.TaskManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ParsingController {
    @Autowired
    private TaskManager taskManager;

    @Autowired
    private HtmlPageService htmlPageService;

    @GetMapping("/dashboard")
    public ModelAndView showDashBoard (){
        ModelAndView modelAndView = new ModelAndView("dashboard");
        modelAndView.addObject("running",taskManager.isRunning() );
        int parsedPageCount = htmlPageService.getTotalPageCount() - htmlPageService.getUnparsedPageCount();
        modelAndView.addObject("totalPageCount", htmlPageService.getTotalPageCount());
        modelAndView.addObject("parsedPageCount", parsedPageCount);
        return modelAndView;
    }

    @PostMapping("/changeWorkingState")
    public String changeWorkingState (@RequestParam boolean state){
        taskManager.setRunning(state);
        return "redirect:/dashboard";
    }

    @PostMapping("/addUrl")
    public String addUrlToList(@RequestParam(value = "urls") String urls) {
        String[] parsedUrls = urls.split("\n");
        for (String url : parsedUrls) {
            htmlPageService.addSeed(url);
        }
        return "redirect:/dashboard";
    }
}
