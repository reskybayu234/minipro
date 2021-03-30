package com.example.kurir254.kurir.controllers;

import com.example.kurir254.kurir.repositories.CourierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/courier/")
public class CourierController {

    @Autowired
    private CourierRepo courierRepo;

    @GetMapping(value="index")
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("courier/index");
        return view;
    }

}
