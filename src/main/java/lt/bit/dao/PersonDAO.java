package lt.bit.dao;

import java.util.List;
import lt.bit.data.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Lina
 */
public interface PersonDAO extends JpaRepository<Person, Integer> {
    @Query("select p from Person p where " 
            + " upper(p.firstName) like upper(concat('%', :filter, '%')) or "
            + " upper(p.lastName) like upper(concat('%', :filter, '%')) or "
            + " upper(p.birthDate) like upper(concat('%', :filter, '%')) or "
            + " upper(p.salary) like upper(concat('%', :filter, '%')) "
            + " order by p.lastName, p.firstName")
    public List<Person> filteredList(@Param("filter") String filter);

    
    @Query("select p from Person p "
            + " order by p.lastName, p.firstName")
    public List<Person> orderedListP(); 
}
