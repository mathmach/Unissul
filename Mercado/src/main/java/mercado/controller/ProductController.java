package mercado.controller;

import mercado.model.entity.Product;
import mercado.model.entity.Rack;
import mercado.model.service.ProductService;
import mercado.model.service.RackService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

    @RequestMapping(value = "/produto", method = RequestMethod.GET)
    public ModelAndView read() {
        ModelAndView mv = new ModelAndView("/product/index");

        try {
            ProductService service = new ProductService();

            List<Product> productList = service.readByCriteria(null, null, null);
            mv.addObject("productList", productList);
            mv.addObject("nav", "product");
        } catch (Exception ex) {
            //TODO resolver isso aqui...
            ex.printStackTrace();
        }

        return mv;
    }

    @RequestMapping(value = "/produto/list", method = RequestMethod.GET, headers = {"X-Requested-With=XMLHttpRequest"})
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("/product/list");

        try {
            ProductService service = new ProductService();

            List<Product> productList = service.readByCriteria(null, null, null);
            mv.addObject("productList", productList);
        } catch (Exception ex) {
            //TODO resolver isso aqui...
            ex.printStackTrace();
        }

        return mv;
    }

    @RequestMapping(value = "/produto/novo", method = RequestMethod.GET)
    public ModelAndView showFormForCreate() {
        ModelAndView mv = new ModelAndView("/product/form");

        try {
            RackService service = new RackService();

            List<Rack> rackList = service.readByCriteria(null, null, null);
            mv.addObject("rackList", rackList);
            mv.addObject("nav", "product");
        } catch (Exception ex) {
            //TODO resolver isso aqui...
            ex.printStackTrace();
        }

        return mv;
    }

    @RequestMapping(value = "/produto/novo", method = RequestMethod.POST)
    public ModelAndView create(String barCode, String name, Long rack, Long quantity) {
        ModelAndView mv = new ModelAndView("redirect:/produto");

        try {
            RackService rService = new RackService();
            ProductService pService = new ProductService();

            Rack r = rService.readById(rack);

            Product product = new Product();
            product.setBarCode(barCode);
            product.setName(name);
            product.setRack(r);
            product.setQuantity(quantity);
            product.setTotal(quantity);

            pService.create(product);
        } catch (Exception ex) {
            //TODO resolver isso aqui...
            ex.printStackTrace();
        }

        return mv;
    }

    @RequestMapping(value = "/produto/{id}/editar", method = RequestMethod.GET)
    public ModelAndView showFormForUpdate(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("/product/form");

        try {
            RackService rService = new RackService();
            ProductService pService = new ProductService();

            List<Rack> rackList = rService.readByCriteria(null, null, null);
            Product product = pService.readById(id);

            mv.addObject("rackList", rackList);
            mv.addObject("product", product);
            mv.addObject("nav", "product");
        } catch (Exception ex) {
            //TODO resolver isso aqui...
            ex.printStackTrace();
        }

        return mv;
    }

    @RequestMapping(value = "/produto/{id}/editar", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable Long id, String barCode, String name, Long rack, Long quantity) {
        ModelAndView mv = new ModelAndView("redirect:/produto");

        try {
            RackService rService = new RackService();

            Rack r = rService.readById(rack);

            ProductService pService = new ProductService();

            Product product = pService.readById(id);
            product.setId(id);
            product.setBarCode(barCode);
            product.setName(name);
            product.setRack(r);
            product.setTotal((product.getTotal() - product.getQuantity()) + quantity);
            product.setQuantity(quantity);

            pService.update(product);
        } catch (Exception ex) {
            //TODO resolver isso aqui...
            ex.printStackTrace();
        }

        return mv;
    }

    // TODO usar o GET para delete é uma falha de segurança.
    @RequestMapping(value = "/produto/{id}/excluir", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("redirect:/produto");

        try {
            ProductService service = new ProductService();

            service.delete(id);
        } catch (Exception ex) {
            //TODO resolver isso aqui...
            ex.printStackTrace();
        }

        return mv;
    }

    @RequestMapping(value = "/product/new", method = RequestMethod.GET, headers = {"X-Requested-With=XMLHttpRequest"})
    public ModelAndView showFormNew() {
        ModelAndView mv = new ModelAndView("/product/formProduct");

        try {
            RackService service = new RackService();

            List<Rack> rackList = service.readByCriteria(null, null, null);
            mv.addObject("rackList", rackList);
        } catch (Exception ex) {
            //TODO resolver isso aqui...
            ex.printStackTrace();
        }

        return mv;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = "newProduct")
    public ModelAndView newProduct(@RequestParam(value = "pagina", required = false) Integer page, String barCode, String name, Long rack, Long quantity) {
        ModelAndView mv;

        if (page == null) {
            mv = new ModelAndView("redirect:/");
        } else {
            mv = new ModelAndView("redirect:/?pagina=" + page);
        }

        try {
            RackService rService = new RackService();
            ProductService pService = new ProductService();

            Rack r = rService.readById(rack);

            Product product = new Product();
            product.setBarCode(barCode);
            product.setName(name);
            product.setRack(r);
            product.setQuantity(quantity);
            product.setTotal(quantity);

            pService.create(product);
        } catch (Exception ex) {
            //TODO resolver isso aqui...
            ex.printStackTrace();
        }

        return mv;
    }

    @RequestMapping(value = "/product/{id}/edit", method = RequestMethod.GET)
    public ModelAndView showFormEdit(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("/product/formProduct");

        try {
            RackService rService = new RackService();
            ProductService pService = new ProductService();

            List<Rack> rackList = rService.readByCriteria(null, null, null);
            Product product = pService.readById(id);
            
            mv.addObject("rackList", rackList);
            mv.addObject("product", product);
        } catch (Exception ex) {
            //TODO resolver isso aqui...
            ex.printStackTrace();
        }

        return mv;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = "editProduct")
    public ModelAndView editProduct(@RequestParam(value = "pagina", required = false) Integer page, Long productId, String barCode, String name, Long rack, Long quantity) {
        ModelAndView mv;

        if (page == null) {
            mv = new ModelAndView("redirect:/");
        } else {
            mv = new ModelAndView("redirect:/?pagina=" + page);
        }

        try {
            RackService rService = new RackService();
            ProductService pService = new ProductService();

            Rack r = rService.readById(rack);

            Product product = pService.readById(productId);
            product.setId(productId);
            product.setBarCode(barCode);
            product.setName(name);
            product.setRack(r);
            product.setTotal((product.getTotal() - product.getQuantity()) + quantity);
            product.setQuantity(quantity);

            pService.update(product);
        } catch (Exception ex) {
            //TODO resolver isso aqui...
            ex.printStackTrace();
        }

        return mv;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = "deleteProduct")
    public ModelAndView deleteRack(@RequestParam(value = "pagina", required = false) Integer page, Long itemId) {
        ModelAndView mv;

        if (page == null) {
            mv = new ModelAndView("redirect:/");
        } else {
            mv = new ModelAndView("redirect:/?pagina=" + page);
        }

        try {
            ProductService service = new ProductService();

            service.delete(itemId);
        } catch (Exception ex) {
            //TODO resolver isso aqui...
            ex.printStackTrace();
        }

        return mv;
    }

}
