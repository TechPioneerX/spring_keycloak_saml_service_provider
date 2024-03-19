package com.keycloak.keneath.controller;

import com.keycloak.keneath.Utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {

    @GetMapping(path = "/allowUserTest")
    public ResponseEntity<Response<Void>> allowTest( /*Principal principal*/ ) {
        // get a successful user login
        return ResponseEntity.ok(new Response<>(
                HttpStatus.OK.value(),
                true,
                "This url is allowed for normal user"
        ));
    }

    @GetMapping(path = "/allowAdminTest")
    public ResponseEntity<Response<Void>> declineTest( /*Principal principal*/ ) {
        // get a successful user login
        return ResponseEntity.ok(new Response<>(
                HttpStatus.OK.value(),
                true,
                "This url is allowed for admin user"
        ));
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception {
        request.logout();
        return "redirect:/";
    }
}
