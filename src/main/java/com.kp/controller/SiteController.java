package com.kp.controller;

import com.kp.model.site.Page;

import com.kp.model.site.Site;
import com.kp.model.user.User;
import com.kp.repository.PageRepository;
import com.kp.repository.UserRepository;
import com.kp.service.PageService;
import com.kp.service.SiteService;
import com.kp.service.UserService;
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

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public @ResponseBody String savePage(@RequestBody String page) {
        return "JSON: The company name: Headoffice";
    }

    @RequestMapping(value = "/angularjs-http-service-ajax-post-json-data-code-example", method = RequestMethod.GET)
    public ModelAndView httpServicePostJSONDataExample( ModelMap model ) {
        return new ModelAndView("httpservice_post_json");
    }

    @RequestMapping(value = "/savecompany_json", method = RequestMethod.POST)
    public  @ResponseBody User saveCompany_JSON(@RequestBody User user )   {

        return user;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Updating User " + id);

        User currentUser = userService.findById(id);

        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        userService.updateUser(user);

        currentUser = userService.findById(id);

        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/pages", method = RequestMethod.GET)
    @ResponseBody
    public String showpages(){
        return siteService.findAllSites().toString();
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

    @RequestMapping(value = "/page/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page> getPage(@PathVariable("id") long id) {
        Page page = pageService.findById(id);
        if (page == null) {
            return new ResponseEntity<Page>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Page>(page, HttpStatus.OK);
    }

    @RequestMapping(value = "/site", method = RequestMethod.POST)
    public ResponseEntity<Long> createSite(@RequestBody Site site, UriComponentsBuilder ucBuilder) {

        if (siteService.isSiteExist(site)) {
            return new ResponseEntity<Long>(HttpStatus.CONFLICT);
        }

        User user = userService.findByUsername(site.getUsername());

        user.addSite(site);
        userService.saveUser(user);
        Long id = user.getSite().get(user.getSite().size()-1).getId();

        System.out.println(String.valueOf(id));

        return new ResponseEntity<Long>(id, HttpStatus.CREATED);
    }



    @RequestMapping(value = "/site/{id}", method = RequestMethod.PUT)
    public @ResponseBody  ResponseEntity<Site> updateSite(@PathVariable("id") long id, @RequestBody Site site) {

        Site currentSite = siteService.findById(id);

        if (currentSite==null) {
            return new ResponseEntity<Site>(HttpStatus.NOT_FOUND);
        }

        currentSite.setSitename(site.getSitename());
        //   currentSite.setComments(site.getComments());
        currentSite.setSiteLogoUrl(site.getSiteLogoUrl());
        currentSite.setDescription(site.getDescription());
        currentSite.setPages(site.getPages());
        //  currentSite.setRate(site.getRate());
        //   currentSite.setTags(site.getTags());

        siteService.updateSite(currentSite);
        return new ResponseEntity<Site>(currentSite, HttpStatus.OK);
    }


    @RequestMapping(value = "/page/{id}", method = RequestMethod.PUT)
    public @ResponseBody  ResponseEntity<Page> updatePage(@PathVariable("id") long id,
                                                          @RequestBody Page page) {

        Page currentPage = pageService.findById(id);

        if (currentPage==null) {
            return new ResponseEntity<Page>(HttpStatus.NOT_FOUND);
        }

        currentPage.setTitle(page.getTitle());
        currentPage.setContent1(page.getContent1());
        currentPage.setContent2(page.getContent2());
        currentPage.setContent3(page.getContent3());
        currentPage.setTemplate(page.getTemplate());

        pageService.savePage(currentPage);
        return new ResponseEntity<Page>(currentPage, HttpStatus.OK);
    }

    @RequestMapping(value = "/site/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Site> deleteSite(@PathVariable("id") long id) {
        Site site = siteService.findById(id);

        if (site == null) {
            return new ResponseEntity<Site>(HttpStatus.NOT_FOUND);
        }

        siteService.deleteSiteById(id);
        return new ResponseEntity<Site>(site,HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/site/{siteId}/page/{pageId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePage(@PathVariable("siteId") long siteId,@PathVariable("pageId") long pageId) {
        Site site = siteService.findById(siteId);
        Page page = pageService.findById(pageId);

        if (page == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        site.getPages().remove(page);
        pageService.deletePageById(pageId);
        siteService.updateSite(site);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/site/", method = RequestMethod.DELETE)
    public ResponseEntity<Site> deleteAllSites() {
        siteService.deleteAllSites();
        return new ResponseEntity<Site>(HttpStatus.NO_CONTENT);
    }
}
