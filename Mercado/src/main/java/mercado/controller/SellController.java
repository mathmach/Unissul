package mercado.controller;

import java.util.List;
import mercado.model.entity.Product;
import mercado.model.entity.Rack;
import mercado.model.service.ProductService;
import mercado.model.service.RackService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SellController {

    @RequestMapping(value = "/vender", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/sell/index");

        try {
            mv.addObject("nav", "sell");
        } catch (Exception ex) {
            //TODO resolver isso aqui...
            ex.printStackTrace();
        }

        return mv;
    }

    @RequestMapping(value = "/vender", method = RequestMethod.POST)
    public ModelAndView sell(RedirectAttributes redir, String barCode, Long quantity) {
        ModelAndView mv = new ModelAndView("redirect:/vender");

        try {
            ProductService service = new ProductService();
            service.removeFromQuantity(barCode, quantity);

            redir.addFlashAttribute("success", "Venda com sucesso!");
        } catch (Exception ex) {
            //TODO resolver isso aqui...
            redir.addFlashAttribute("error", ex.getMessage());
        }

        return mv;
    }
}
