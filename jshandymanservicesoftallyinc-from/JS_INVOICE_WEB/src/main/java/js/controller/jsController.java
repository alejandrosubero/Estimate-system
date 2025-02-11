package js.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Controller
public class jsController {

    @GetMapping(value = "/ingreso")
    public String ingreso1(HttpServletRequest request, HttpServletResponse response) { 
        try {
            System.out.println("intercepta 1 controller  ");
        } catch (Exception e) {
            e.printStackTrace();
        }        
        return "forward:/ingreso";
    }

    @GetMapping(value = "/#")
    public String ingreso2(HttpServletRequest request, HttpServletResponse response) { 
        try {
            System.out.println("intercepta 2 controller  ");
        } catch (Exception e) {
            e.printStackTrace();
        }        
        return "forward:/";
    }

    @GetMapping(value = "/ingress")
    public String ingreso3(HttpServletRequest request, HttpServletResponse response) { 
        try {
            System.out.println("intercepta 3  controller  ");
        } catch (Exception e) {
            e.printStackTrace();
        }        
        return "forward:/#/ingreso";
    }

    @GetMapping(value = "/test")
    public String ingresoTest(HttpServletRequest request, HttpServletResponse response) { 
        try {
            System.out.println("intercepta test  controller  ");
        } catch (Exception e) {
            e.printStackTrace();
        }        
        return "test";
    }

}