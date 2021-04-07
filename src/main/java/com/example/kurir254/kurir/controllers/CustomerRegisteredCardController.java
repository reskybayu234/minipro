package com.example.kurir254.kurir.controllers;

import com.example.kurir254.kurir.repositories.CustomerRegisteredCardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/customerregisteredcard/")
public class CustomerRegisteredCardController {

    @Autowired
    private CustomerRegisteredCardRepo customerRegisteredCardRepo;

    @GetMapping(value="index")
    public ModelAndView index(){
        ModelAndView view = new ModelAndView( "customerregisteredcard/index");
        return view;
    }

}
