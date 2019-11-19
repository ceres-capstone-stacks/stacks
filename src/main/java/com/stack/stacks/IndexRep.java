package com.stack.stacks;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class IndexRep {


    @GetMapping("/")
    public String welcome() {
        return "home";

    }
}
