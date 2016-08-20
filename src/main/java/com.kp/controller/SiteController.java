package com.kp.controller;

import com.kp.model.site.Page;

import com.kp.model.site.Site;
import com.kp.repository.PageRepository;
import com.kp.service.PageService;
import com.kp.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@Controller
public class SiteController {

    @Autowired
    PageRepository repository;

    @Autowired
    PageService pageService;

    @Autowired
    SiteService siteService;

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

    @RequestMapping(value = "/site/", method = RequestMethod.GET)
    public ResponseEntity<List<Site>> listAllSites() {

        List<Site> sites = siteService.findAllSites();
        if(sites.isEmpty()){
            return new ResponseEntity<List<Site>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }

        return new ResponseEntity<List<Site>>(sites, HttpStatus.OK);
    }

    @RequestMapping(value = "/site/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Site> getSite(@PathVariable("id") long id) {
        Site site = siteService.findById(id);
        if (site == null) {
            return new ResponseEntity<Site>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Site>(site, HttpStatus.OK);
    }

    @RequestMapping(value = "/site", method = RequestMethod.POST)
    public ResponseEntity<Void> createSite(@RequestBody Site site, UriComponentsBuilder ucBuilder) {

        if (siteService.isSiteExist(site)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        siteService.saveSite(site);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/site/{id}").buildAndExpand(site.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/site/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Site> updateSite(@PathVariable("id") long id, @RequestBody Site site) {

        Site currentSite = siteService.findById(id);

        if (currentSite==null) {
            return new ResponseEntity<Site>(HttpStatus.NOT_FOUND);
        }

        currentSite.setSitename(site.getSitename());
     //   currentSite.setComments(site.getComments());
        currentSite.setDescription(site.getDescription());
    //    currentSite.setPages(site.getPages());
      //  currentSite.setRate(site.getRate());
     //   currentSite.setTags(site.getTags());

        siteService.updateSite(currentSite);
        return new ResponseEntity<Site>(currentSite, HttpStatus.OK);
    }

    @RequestMapping(value = "/site/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Site> deleteSite(@PathVariable("id") long id) {
        Site site = siteService.findById(id);

        if (site == null) {
            return new ResponseEntity<Site>(HttpStatus.NOT_FOUND);
        }

        siteService.deleteSiteById(id);
        return new ResponseEntity<Site>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/site/", method = RequestMethod.DELETE)
    public ResponseEntity<Site> deleteAllSites() {
        siteService.deleteAllSites();
        return new ResponseEntity<Site>(HttpStatus.NO_CONTENT);
    }
}
