package lt.bit.controller;

import java.util.List;
import lt.bit.dao.AddressDAO;
import lt.bit.dao.ContactDAO;
import lt.bit.dao.PersonDAO;
import lt.bit.data.Address;
import lt.bit.data.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lina
 */
@RestController
@RequestMapping("/rest/person/{idP}/contact")
public class ControllerRestC {
  
    @Autowired
    private PersonDAO personDAO;

    @Autowired
    private ContactDAO contactDAO;

    @GetMapping
    public List<Contact> listC(@PathVariable("idP") Integer idP) {
        return contactDAO.orderedListC(idP);
    }

    @GetMapping("{idC}")
    public Contact getC(@PathVariable(value = "idC") Integer idC) {
        return contactDAO.getOne(idC);
    }

    @Transactional
    @DeleteMapping("{idC}")
    public void deleteC(@PathVariable(value = "idC") Integer idC) {
       contactDAO.deleteById(idC);
    }

    @Transactional
    @PostMapping
    public Contact addC(@PathVariable(value="idP") Integer idP, @RequestBody Contact nc){
        nc.setPerson(personDAO.getOne(idP)); //sita pridejau ir ta path variable virsuje pridejau
        contactDAO.save(nc);
        System.out.println(".................");
        System.out.println("nc" + nc);
        return nc;
    }
    
    @Transactional
    @PutMapping("{idC}")    
    public Contact updateC(@PathVariable(value="idC") Integer idC, @RequestBody Contact nc){  
       Contact c= contactDAO.getOne(idC);
       c.setContact(nc.getContact());
       c.setType(nc.getType()); 
       return c; // ??
    } 
}
