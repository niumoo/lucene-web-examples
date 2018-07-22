package net.codingme.lucene.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author DarcyNiu
 * @date 2018/4/6 22:22
 */
@Controller
@RequestMapping(value = "/html")
public class HtmlController {

    @RequestMapping(value = {"/index"},method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("username","Darcy");
        return "index";
    }
}