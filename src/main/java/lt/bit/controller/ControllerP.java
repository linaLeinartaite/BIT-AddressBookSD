package lt.bit.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import lt.bit.dao.PersonDAO;

import lt.bit.data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Lina
 */
@Controller
public class ControllerP {

    @Autowired //
    private PersonDAO personDAO;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

//    public List<Person> findByEntry(String entry) {
//        List<Person> l = new ArrayList();
//        return l;
//    }
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView getPersons(@RequestParam(value = "filter", required = false) String filter) {
        ModelAndView mav = new ModelAndView("people");
        List<Person> listP;
        if (filter == null) {
           // listP = personDAO.findAll(Sort.by("lasttName"));
             listP = personDAO.orderedListP();
        } else {
            listP = personDAO.filteredList(filter);
        }
        mav.addObject("listP", listP);// sitas bus pasiekiamas su request.getAttribute("listP");
        return mav;
    }

    @GetMapping(value = "/delete") //cia jei naudoji DeleteMapping reikia naudoti form'a (kur action butu DELETE)
    @Transactional
    public String deletePerson(@PathParam("idP") Integer idP) {
        personDAO.deleteById(idP);
        return "redirect:./";
    }

//    @RequestMapping(value = "edit", method = {RequestMethod.GET, RequestMethod.POST})
    @GetMapping("edit")
    public ModelAndView editP(@RequestParam(value = "idP", required = false) Integer idP) {
        ModelAndView mav = new ModelAndView("editP");
        if (idP != null) {
            Person p = personDAO.getOne(idP);
            mav.addObject("p", p);
        }
        return mav;
    }

    @PostMapping(value = "/save")
    @Transactional //!! is org.spring.framework !! (ne javax) reikalinga nes save'inimui naudosim tranzakcijas
    public String saveP(@RequestParam(value = "idP", required = false) Integer idP,
            @RequestParam(value = "fn") String firstName,
            @RequestParam(value = "ln") String lastName,
            @RequestParam(value = "bd") String birthDate,
            @RequestParam(value = "s") String salaryS //cia pakeiciau i String  (is BigDecimal) kad raides vest negaletu
    ) {
        Person np = new Person();
        if (firstName.trim().equals("") || lastName.trim().equals("")) {
            return "redirect:./";
        } else {
            np.setFirstName(firstName.trim());
            np.setLastName(lastName.trim());
        }
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(birthDate));
            cal.set(Calendar.HOUR, 12);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            np.setBirthDate(cal.getTime());
//            np.setBirthDate(sdf.parse(birthDate));            
        } catch (ParseException ex) {
            np.setBirthDate(null);
        }
        try {
            BigDecimal salary = new BigDecimal(salaryS.trim());
            np.setSalary(salary);
        } catch (Exception ex) {
            np.setSalary(null);
        }

        np.setId(idP);
        personDAO.save(np); //sis methodas tinka ir addP ir updateP >> jei idnull tai jis saugo, jei ne updatina
        return "redirect:./";
    }
}
