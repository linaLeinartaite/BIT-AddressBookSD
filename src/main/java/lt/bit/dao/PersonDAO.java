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
            + " upper(p.lastName) like upper(concat('%', :filter, '%')) "
            + " order by p.firstName")
    public List<Person> filteredList(@Param("filter") String filter);
// kai kviesim jis sita "filter" istatys i ta vieta kur uzklausoje filter
    //cia velgi nebuvo rasyta methodo implementacija, bet tik deklaravo methoda!
    //springas uskure si methoda uz mus ir kaip parametra, ta kur anotuotas su @Param 
    //perduos i query kintamajam (zymimas su : >> :filter
    
    @Query("select p from Person p "
            + " order by p.firstName")
    public List<Person> orderedListP(); // sitaas turi grazinti isrusiuota pagal varda (galiu deti si methoda vietoj 
    //findAll(SortedBy(firstName);
    
//    @Query(name="Person.findAll")
//    public List<Person> orderedList2();
//    
}
