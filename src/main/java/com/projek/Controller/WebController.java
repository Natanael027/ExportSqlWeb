package com.projek.Controller;

import com.projek.DAO.DataDao;
import com.projek.Entity.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class WebController {
    @Autowired
    private DataDao dao;

    @Autowired
    public WebController(DataDao dao) {
        this.dao = dao;
    }
    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }
    @GetMapping(value = {"/", "/home"})
    public String home1(Model map, @Param("keyword") String keyword, HttpServletRequest request) {
        List<Data> results = dao.findDatabyStatus3();

        System.out.println(results.toString());
        System.out.println(results.size());
        System.out.println("Session :: "+request.getSession().toString());

        map.addAttribute("listResults", results);
        return "index";
    }
    @GetMapping("/update/{id}/{status}")
    public String updateUserEnabledStatus(@PathVariable("id") Integer id, @PathVariable("status") Integer status,
                                          RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response)
    {
        Data data = dao.findDataById(Long.valueOf(id));
        System.out.println("enabled :: "+status);
        System.out.println("req :"+request.getRemoteUser() + " || " + response.getStatus());
        System.out.println("Session :: "+request.getSession().toString());

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
        String strdate = sdf.format(date);

//        String status = enabled ? "Done" : "On Progress";
        if (status == 1 ){
            data.setStatus(1);
            data.setDescription("Valid by CMS");
            data.setUpdated_at(strdate);
            data.setUpdated_by(request.getRemoteUser());
        } else if (status == 2) {
            data.setStatus(2);
            data.setDescription("Not Valid by CMS");
            data.setUpdated_at(strdate);
            data.setUpdated_by(request.getRemoteUser());
        }
        dao.saveData(data);
        String message = "Data is Updated";
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:/home";
    }

}
