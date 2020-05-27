package lt.bit.controller;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import lt.bit.dao.AddressDAO;
import lt.bit.dao.PersonDAO;
import lt.bit.data.Address;
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
@RequestMapping("/{idP}/addresses/")
public class ControllerA {

    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private AddressDAO addressDAO;

    @GetMapping
    public ModelAndView getAddresses(@PathVariable Integer idP) {
        ModelAndView mav = new ModelAndView("addresses");
        Person p = personDAO.getOne(idP);
        if (p != null) {
            mav.addObject("p", p);
            mav.addObject("listA", p.getAddresses());
        } else {
            List<Address> listA = new ArrayList();
            mav.addObject("listA", listA);
        }
//        sita zemiau galim parasyti vietoj to virsuj irkad filtruotu; 
//        orderedByPersonId== musu paciu parasyta uzklausa  wirs Address klases kaip @NamedQuery

//        ModelAndView mav = new ModelAndView("addresses");
//        mav.addObject("listA", addressDAO.orderedByPersonId(idP));
        return mav;
    }

    @Transactional //is org.springframework... (ne is javax)
    @GetMapping(value = "/delete") //cia jei naudoji DeleteMapping reikia naudoti form'a (kur action butu DELETE)
    public String deleteAddress(@PathParam("idA") Integer idA) {
        addressDAO.deleteById(idA);
        return "redirect:./";
    }

    @GetMapping("edit")
    public ModelAndView editA(@RequestParam(value = "idA", required = false) Integer idA) {
        ModelAndView mav = new ModelAndView("editA");
        if (idA != null) {
            Address a = addressDAO.getOne(idA);
            mav.addObject("a", a);
        }
        return mav;
    }

    @Transactional
    @PostMapping(value = "/save")
    public String saveA(@RequestParam(value = "idA", required = false) Integer idA,
            @RequestParam(value = "a") String address,
            @RequestParam(value = "c") String city,
            @RequestParam(value = "pc") String postCode,
            @PathVariable Integer idP
    ) {
        Address na;
        if (idA == null) {
            na = new Address();
            na.setPerson(personDAO.getOne(idP));
        } else {
            na = addressDAO.getOne(idA);
        }
        if (na != null) {
            if (address.trim().equals("") || city.trim().equals("") || postCode.trim().equals("")) {
                return "redirect:./";
            } else {
                na.setAddress(address.trim());
                na.setCity(city.trim());
                na.setPostCode(postCode.trim());
            }
            addressDAO.save(na);
        }
        return "redirect:./";
    }
}
