package lt.bit.controller;

import java.util.List;
import lt.bit.dao.AddressDAO;
import lt.bit.dao.PersonDAO;
import lt.bit.data.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lina
 */
@RestController
@RequestMapping("/rest/person/{idP}/address")
public class ControllerRestA {

    @Autowired
    private PersonDAO personDAO;

    @Autowired
    private AddressDAO addressDAO;

    @GetMapping
    public List<Address> listA(@PathVariable("idP") Integer idP) {
        return addressDAO.orderedListA(idP);
    }

    @GetMapping("{idA}")
    public Address getA(@PathVariable(value = "idA") Integer idA) {
        return addressDAO.getOne(idA);
    }

    @Transactional
    @DeleteMapping("{idA}")
    public void deleteA(@PathVariable(value="idA") Integer idA) {
        addressDAO.deleteById(idA);
    }

    @Transactional
    @PostMapping
    public Address addA(@PathVariable(value="idP") Integer idP, @RequestBody Address na){
        na.setPerson(personDAO.getOne(idP)); //sita pridejau ir ta path variable virsuje pridejau
        addressDAO.save(na);
        System.out.println(".................");
        System.out.println("a" + na);
        return na;
    }
    
    @Transactional
    @PutMapping("{idA}")    
    public Address updateA(@PathVariable(value="idA") Integer idA, @RequestBody Address na){  
       Address a= addressDAO.getOne(idA);
       a.setAddress(na.getAddress());
       a.setCity(na.getCity()); 
       a.setPostCode(na.getPostCode());
       return a; // ??
    } 

}
