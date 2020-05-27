package lt.bit.dao;

import java.util.List;
import lt.bit.data.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Lina
 */
public interface AddressDAO extends JpaRepository<Address, Integer> {

//    public List<Address> orderedByPersonId(@Param("idP") Integer personId);
    
    @Query("select a from Address a where a.person.id = :idP order by a.address")
    public List<Address> orderedListA(@Param("idP") Integer idP);
}
