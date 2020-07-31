package mercado.controller;

import java.util.List;
import mercado.model.entity.Product;
import mercado.model.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NotificationController {

    @RequestMapping(value = "/notification", method = RequestMethod.GET, headers = {"X-Requested-With=XMLHttpRequest"})
    public ModelAndView notification() {
        ModelAndView mv = new ModelAndView("/common/notification");

        try {
            ProductService service = new ProductService();
            List<Product> productList = service.readByCriteria(null, null, null);
            for (Product product : productList) {
                if (product.getQuantity() <= ((product.getTotal() * 0.1) + 1)) {
                    mv.addObject("notificationDanger", "Existem itens com pouco estoque!");
                } else if (product.getQuantity() <= ((product.getTotal() * 0.5) + 1)) {
                    mv.addObject("notificationWarning", "Existem itens com menos da metade do estoque!");
                }
            }
        } catch (Exception ex) {
            //TODO resolver isso aqui...
        }

        return mv;
    }

}
