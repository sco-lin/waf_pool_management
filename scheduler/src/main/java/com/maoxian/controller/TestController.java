package com.maoxian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("test")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping()
    public ResponseEntity<String> schedule(HttpServletRequest request, HttpServletResponse response, RequestEntity<String> requestEntity) throws ServletException, IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-UUID", "a");
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        System.out.println("=====");
        ResponseEntity<String> result = restTemplate.exchange("http://localhost:8080/email/test", HttpMethod.GET, entity, String.class);

        System.out.println(result.getHeaders());
        return result;
    }

    @GetMapping("te")
    public void schedule2(HttpServletRequest request, RequestEntity<String> requestEntity) {
        System.out.println(request.getRequestURI());
        System.out.println(request.getRequestURL());


    }

}
