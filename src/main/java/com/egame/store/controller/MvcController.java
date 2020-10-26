package com.egame.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MvcController {

	@GetMapping("/{page}")
    public String page(@PathVariable String page) {
        return page;
    }
}
