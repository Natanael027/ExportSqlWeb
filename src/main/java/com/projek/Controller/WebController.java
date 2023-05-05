package com.projek.Controller;

import com.projek.DAO.ExporterDao;
import com.projek.Entity.Exporter;
import com.projek.Service.ExporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class WebController {
    @Autowired
    ExporterService service;

    @Autowired
    ExporterDao dao;

    @GetMapping(value = {"/", "/home"})
    public String home1(Model map) {
        Exporter exporter = new Exporter();

        map.addAttribute("export", exporter);

      return "index";
    }
    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @PostMapping("/submit")
    public String submitForm(HttpServletResponse response, Exporter exporter) throws IOException {
        System.out.println(exporter.toString());
        List<String> list = new ArrayList<String>(Arrays.asList(exporter.getMsisdn().split(",")));

        String queryResult = service.genFileNew(list, exporter.getType());
        System.out.println("result >>" + queryResult);

        dao.tesInsert(queryResult);

        return "redirect:/home";
    }

}
