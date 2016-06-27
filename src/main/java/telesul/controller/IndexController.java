/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telesul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lmohan
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String index() {
        return "CallClassification";
    }


}
