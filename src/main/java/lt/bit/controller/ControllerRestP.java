package lt.bit.controller;

import java.util.List;
import lt.bit.dao.PersonDAO;
import lt.bit.data.Person;
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
//sis kontroleris 
//jsp file'as (kuris irgi yra servletas) ji grazina html, o kai darom 
//su REST'u sitas daiktas grazina list'a, o jersis sita lista paversdavo i JSON'a
@RestController //tai spec kontroleris ir Spring zino kadtie kascia paklsius , 
//grazins ne paprastaview'a o resultata ver i JSON
@RequestMapping("/rest/person")
public class ControllerRestP {

    @Autowired
    private PersonDAO personDAO;

    @GetMapping
    public List<Person> listP() {
        return personDAO.orderedListP();
    }
    
    @GetMapping("{idP}")
    public Person getP(@PathVariable(value="idP") Integer idP){    
        return personDAO.getOne(idP);
    }
    
    @Transactional
    @DeleteMapping("{idP}")
    public void deleteP(@PathVariable(value="idP") Integer idP){    
       personDAO.deleteById(idP);
    }
    
    
    @Transactional
    //@PostMapping("{idP}")    
    @PostMapping
    public Person addP(@RequestBody Person p){     //reiskia kad gaini visa streema, springas praleidzia per jacksona, kuris sukuria viska is narsykles
        System.out.println("************************");
        System.out.println("p: "+ p);
        personDAO.save(p);
       return p;
    }
    
    @Transactional
    @PutMapping("{idP}")    
    public Person updateP(@PathVariable(value="idP") Integer idP, @RequestBody Person np){  
       Person p = personDAO.getOne(idP);
       p.setFirstName(np.getFirstName());
       p.setLastName(np.getLastName());
       p.setBirthDate(np.getBirthDate());
       p.setSalary(np.getSalary()); 
//       personDAO.save(p);
       return np;
    }    
}

