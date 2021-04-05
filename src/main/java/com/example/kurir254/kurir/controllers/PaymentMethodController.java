package com.example.kurir254.kurir.controllers;

import com.example.kurir254.kurir.repositories.CourierRepo;
import com.example.kurir254.kurir.repositories.PaymentMethodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/paymentmethod/")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodRepo paymentMethodRepo;

    @GetMapping(value="index")
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("paymentmethod/index");
        return view;
    }

}
