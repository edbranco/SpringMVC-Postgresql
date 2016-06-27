/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telesul.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import telesul.model.CallClassification;
import telesul.service.CallClassificationService;
//import telesul.service.OccurenceService;

/**
 *
 * @author ebranco
 */
@Controller
@RequestMapping("/callclassify")
public class CallClassificationController {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(CallClassificationController.class);

    @Autowired
    CallClassificationService callClassificationService;

    @RequestMapping(value = "save", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody
    ResponseEntity<String> saveTab(@RequestBody CallClassification callClassification) {
        callClassificationService.saveCallClassification(callClassification);
        return new ResponseEntity<>("Salvo com Successo", HttpStatus.OK);

    }
}
