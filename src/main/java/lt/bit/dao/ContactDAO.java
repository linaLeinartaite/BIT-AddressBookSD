package lt.bit.dao;

import java.util.List;
import lt.bit.data.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Lina
 */
public interface ContactDAO extends JpaRepository <Contact,Integer> {
    
    @Query("select c from Contact c where c.person.id = :idP order by c.contact")
    public List<Contact> orderedListC(@Param("idP") Integer idP);
}
