package com.kp.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.kp.service.CommentService;
import com.kp.transfer.UrlTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 8/3/16.
 */


@Controller
public class MainController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("time", new Date());
        return "welcome";
    }




    @RequestMapping("/foo")
    public String foo(Map<String, Object> model) {
        throw new RuntimeException("Foo");
    }

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    @ResponseBody
    public String showComments(){
        return commentService.findAll().toString();
    }

    @RequestMapping(value = "/image/", method = RequestMethod.POST)
    public ResponseEntity<UrlTransfer> getAvatarImage(@RequestBody String file)
    {
        String url="";
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "ddn3rvx8c",
                "api_key", "174231233227927",
                "api_secret", "deiKCXWmKj71V6ap9oHSZXo4E1k"));
        try{
            url= (String) cloudinary.uploader().upload(file,ObjectUtils.emptyMap()).get("url");
        } catch (IOException e){
            System.out.println(e);
        }
        return new ResponseEntity<UrlTransfer>(new UrlTransfer(url), HttpStatus.OK);
    }


}

