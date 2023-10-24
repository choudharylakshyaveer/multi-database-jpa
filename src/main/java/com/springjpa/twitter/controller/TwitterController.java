package com.springjpa.twitter.controller;

import com.springjpa.twitter.entitiy.TwitterIds;
import com.springjpa.twitter.service.TwitterDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TwitterController {

    @Autowired
    TwitterDbService twitterDbService;

    @RequestMapping(value = "/getAllTwitterIds", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<TwitterIds> getAllIds(){
        return twitterDbService.getAllTwitterEntities();
    }
}
