package lt.bit.controller;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import lt.bit.dao.ContactDAO;
import lt.bit.dao.PersonDAO;
import lt.bit.data.Contact;
import lt.bit.data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Lina
 */
@Controller
@RequestMapping("/{idP}/contacts/") //there is big difference in path-formation if you use "/{idP}/contacts/" or "/{idP}/contacts" 
//e.g with firt one to get back you use ../.. and with second one ../ ; also with second in href= it adds address e.g. in front of delete 
public class ControllerC {

    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private ContactDAO addressDAO;

    @GetMapping
    public ModelAndView getContacts(@PathVariable Integer idP) {
        ModelAndView mav = new ModelAndView("contacts");
        Person p = personDAO.getOne(idP);
        if (p != null) {
            mav.addObject("p", p);
            mav.addObject("listC", p.getContacts());
        } else {
            List<Contact> listC = new ArrayList();
            mav.addObject("listC", listC);
        }
        return mav;
    }

    @Transactional //is org.springframework... (ne is javax)
    @GetMapping(value = "/delete") //cia jei naudoji DeleteMapping reikia naudoti form'a (kur action butu DELETE)
    public String deleteContact(@PathParam("idC") Integer idC) {
        addressDAO.deleteById(idC);
        return "redirect:./";
    }

    @GetMapping("edit")
    public ModelAndView editC(@RequestParam(value = "idC", required = false) Integer idC) {
        ModelAndView mav = new ModelAndView("editC");
        if (idC != null) {
            Contact c = addressDAO.getOne(idC);
            mav.addObject("c", c);
        }
        return mav;
    }

    @Transactional
    @PostMapping(value = "/save")
    public String saveA(@RequestParam(value = "idC", required = false) Integer idC,
            @RequestParam(value = "ct") String type,
            @RequestParam(value = "co") String contact,            
            @PathVariable Integer idP
    ) {
        Contact nc;
        if (idC == null) {
            nc = new Contact();
            nc.setPerson(personDAO.getOne(idP));
        } else {
            nc = addressDAO.getOne(idC);
        }
        if (nc != null) {
            if (type.trim().equals("") || contact.trim().equals("")) {
                return "redirect:./";
            } else {
                nc.setType(type.trim());
                nc.setContact(contact.trim());              
            }
            addressDAO.save(nc);
        }
        return "redirect:./";
    }
}
