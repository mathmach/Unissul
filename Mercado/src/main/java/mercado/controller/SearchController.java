package mercado.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class SearchController {

    @RequestMapping(value = "/procurar", method = RequestMethod.POST)
    public ModelAndView search(String search) {
        ModelAndView mv = new ModelAndView("redirect:/procurar?c=" + search);

        return mv;
    }

    @RequestMapping(value = "/procurar", method = RequestMethod.GET)
    public ModelAndView result(@RequestParam(value = "c", required = true) String criteria) {
        ModelAndView mv = new ModelAndView("/search/index");

        Map<Long, Object> criteriaMap = new HashMap<Long, Object>();
        criteriaMap.put(1L, criteria);

        try {
            RackService rService = new RackService();
            ProductService pService = new ProductService();

            List<Rack> rackList = rService.readByCriteria(criteriaMap, null, null);
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
