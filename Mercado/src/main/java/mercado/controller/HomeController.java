package mercado.controller;

import java.util.List;
import mercado.model.entity.Product;
import mercado.model.entity.Rack;
import mercado.model.service.ProductService;
import mercado.model.service.RackService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(@RequestParam(value = "pagina", required = false) Long currentPage) {
        ModelAndView mv = new ModelAndView("/home/index");

        try {
            RackService rService = new RackService();
            ProductService pService = new ProductService();

            List<Rack> rackList = rService.readByCriteria(null, null, null);
            List<Product> productList = pService.readByCriteria(null, null, null);

            mv.addObject("rackList", rackList);
            mv.addObject("productList", productList);
            mv.addObject("nav", "home");
        } catch (Exception ex) {
            //TODO resolver isso aqui...
            ex.printStackTrace();
        }

        return mv;
    }
}
