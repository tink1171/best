package com.kp.controller;

import com.kp.model.Page;

import com.kp.repository.PageRepository;
import com.kp.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SiteController {

    @Autowired
    PageRepository repository;

    @Autowired
    PageService pageService;

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public @ResponseBody String savePage(@RequestBody String page) {
        return "JSON: The company name: Headoffice";
    }

    @RequestMapping(value = "/angularjs-http-service-ajax-post-json-data-code-example", method = RequestMethod.GET)
    public ModelAndView httpServicePostJSONDataExample( ModelMap model ) {
        return new ModelAndView("httpservice_post_json");
    }

    @RequestMapping(value = "/savecompany_json", method = RequestMethod.POST)
    public  @ResponseBody Page saveCompany_JSON(@RequestBody Page page )   {

        pageService.savePage(page);
        return page;
    }

    @RequestMapping(value = "/pages", method = RequestMethod.GET)
    @ResponseBody
    public String showpages(){
        return repository.findAll().toString();
    }


    @RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Page showpages(@PathVariable("id") long id){
        Page page = repository.findById(id);
        return page;
    }
}
