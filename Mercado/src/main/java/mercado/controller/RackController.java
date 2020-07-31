package mercado.controller;

import mercado.model.entity.Rack;
import mercado.model.service.RackService;
import java.util.List;
import mercado.model.entity.Product;
import mercado.model.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RackController {

    @RequestMapping(value = "/prateleira", method = RequestMethod.GET)
    public ModelAndView read() {
        ModelAndView mv = new ModelAndView("/rack/index");

        try {
            RackService rService = new RackService();
            ProductService pService = new ProductService();

            List<Rack> rackList = rService.readByCriteria(null, null, null);
            List<Product> productList = pService.readByCriteria(null, null, null);
            mv.addObject("rackList", rackList);
            mv.addObject("productList", productList);
            mv.addObject("nav", "rack");
        } catch (Exception ex) {
            //TODO resolver isso aqui...
            ex.printStackTrace();
        }

        return mv;
    }

    @RequestMapping(value = "/prateleira/list", method = RequestMethod.GET, headers = {"X-Requested-With=XMLHttpRequest"})
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("/rack/list");

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

    @RequestMapping(value = "/prateleira/novo", method = RequestMethod.GET)
    public ModelAndView showFormForCreate() {
        ModelAndView mv = new ModelAndView("/rack/form");

        mv.addObject("nav", "rack");

        return mv;
    }

    @RequestMapping(value = "/prateleira/novo", method = RequestMethod.POST)
    public ModelAndView create(String name) {
        ModelAndView mv = new ModelAndView("redirect:/prateleira");

        try {
            RackService service = new RackService();

            Rack rack = new Rack();
            rack.setName(name);

            service.create(rack);
        } catch (Exception ex) {
            //TODO resolver isso aqui...   
            ex.printStackTrace();
        }

        return mv;
    }

    @RequestMapping(value = "/prateleira/{id}/editar", method = RequestMethod.GET)
    public ModelAndView showFormForUpdate(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("/rack/form");

        try {
            RackService service = new RackService();

            Rack rack = service.readById(id);
            mv.addObject("rack", rack);
            mv.addObject("nav", "rack");
        } catch (Exception ex) {
            //TODO resolver isso aqui...   
            ex.printStackTrace();
        }

        return mv;
    }

    @RequestMapping(value = "/prateleira/{id}/editar", method = RequestMethod.POST)
    public ModelAndView edit(@PathVariable Long id, String name) {
        ModelAndView mv = new ModelAndView("redirect:/prateleira");

        try {
            RackService service = new RackService();

            Rack rack = new Rack();
            rack.setId(id);
            rack.setName(name);

            service.update(rack);
        } catch (Exception ex) {
            //TODO resolver isso aqui...   
            ex.printStackTrace();
        }

        return mv;
    }

    // TODO usar o GET para delete é uma falha de segurança.
    @RequestMapping(value = "/prateleira/{id}/excluir", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("redirect:/prateleira");

        try {
            RackService service = new RackService();

            service.delete(id);
        } catch (Exception ex) {
            //TODO resolver isso aqui...   
            ex.printStackTrace();
        }

        return mv;
    }

    @RequestMapping(value = "/rack/new", method = RequestMethod.GET, headers = {"X-Requested-With=XMLHttpRequest"})
    public ModelAndView showFormNew() {
        ModelAndView mv = new ModelAndView("/rack/formRack");

        return mv;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = "newRack")
    public ModelAndView newPost(@RequestParam(value = "pagina", required = false) Integer page, String name) {
        ModelAndView mv;

        if (page == null) {
            mv = new ModelAndView("redirect:/");
        } else {
            mv = new ModelAndView("redirect:/?pagina=" + page);
        }

        try {
            RackService service = new RackService();

            Rack rack = new Rack();
            rack.setName(name);

            service.create(rack);
        } catch (Exception ex) {
            //TODO resolver isso aqui...   
            ex.printStackTrace();
        }

        return mv;
    }

    @RequestMapping(value = "/rack/{id}/edit", method = RequestMethod.GET, headers = {"X-Requested-With=XMLHttpRequest"})
    public ModelAndView showFormEdit(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("/rack/formRack");

        try {
            RackService service = new RackService();

            Rack rack = service.readById(id);
            mv.addObject("rack", rack);
        } catch (Exception ex) {
            //TODO resolver isso aqui...   
            ex.printStackTrace();
        }

        return mv;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = "editRack")
    public ModelAndView editRack(@RequestParam(value = "pagina", required = false) Integer page, Long id, String name) {
        ModelAndView mv;

        if (page == null) {
            mv = new ModelAndView("redirect:/");
        } else {
            mv = new ModelAndView("redirect:/?pagina=" + page);
        }

        try {
            RackService service = new RackService();

            Rack rack = new Rack();
            rack.setId(id);
            rack.setName(name);

            service.update(rack);
        } catch (Exception ex) {
            //TODO resolver isso aqui...   
            ex.printStackTrace();
        }

        return mv;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = "deleteRack")
    public ModelAndView deleteRack(@RequestParam(value = "pagina", required = false) Integer page, Long itemId) {
        ModelAndView mv;

        if (page == null) {
            mv = new ModelAndView("redirect:/");
        } else {
            mv = new ModelAndView("redirect:/?pagina=" + page);
        }

        try {
            RackService service = new RackService();

            service.delete(itemId);
        } catch (Exception ex) {
            //TODO resolver isso aqui...   
            ex.printStackTrace();
        }

        return mv;
    }

}
