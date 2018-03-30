package com.first.boot.springboot.controller;

import com.first.boot.springboot.service.SumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SumController {
    @Autowired
    private SumService sumService;

    @GetMapping("/sum")
    public ModelAndView showInput() {
        return new ModelAndView("input");
    }

    @PostMapping("/sum")
    public ModelAndView calc(@RequestParam int a,
                             @RequestParam int b) {

        int sum = sumService.sum(a, b);

        ModelAndView result = new ModelAndView("output");
        result.addObject("sum", sum);
        return result;

    }
}
