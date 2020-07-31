package mercado.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mercado.model.JasperSoft;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReportController {

    @RequestMapping(value = "/relatorio", method = RequestMethod.GET)
    public ModelAndView report(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("redirect:/");

        try {
            // gera relatório
            JasperSoft.generateReport(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return mv;
    }

}
