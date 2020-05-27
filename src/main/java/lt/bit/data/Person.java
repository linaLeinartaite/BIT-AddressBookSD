
package lt.bit.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Lina
 */
@Entity //reiskia kad sitos klasess atstovai bus saugomi i DB
@Table(name = "person") //jei nebutu anotacijos Table tai saugotu ??? (kazkas su Diz MAz raide)
//@NamedQuery(name="Person.byId", query="")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Person {

    @Id //reiskia kad tai DB'e bus primary key'us; ir name nerasom bo sutampa id == id (ir DB)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //kodel Integer o ne int bus aiskiau kai dirbsim su duomenu bazem;
    // private static int  nextId=1; 
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthDate;
    private BigDecimal salary;

//    @Transient //reiskia kad nesaugosim i duomenu baze
    @OneToMany(mappedBy = "person") //kai mappedBy tai join column'a uzkomentuojam
   // @JoinColumn(name = "person_id")
    @JsonIgnore
    private List<Address> addresses;
    
//    @Transient    
    @OneToMany(mappedBy = "person") //pasivi puse turi buti ta kuri turi OneTomany t.y ta kuri turi lista (tai va sita ir yra)
    //@JoinColumn(name = "person_id")
    @JsonIgnore
    private List<Contact> contacts;

    public Person() {
       // List <Address> addresses = new ArrayList<>();
       // List <Contact> contacts = new ArrayList<>();
        //DEST : cia sukurti tuscius arrejus addresses ir contacts
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate + ", salary=" + salary + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

}
