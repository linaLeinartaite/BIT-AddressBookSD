package lt.bit.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Lina
 */
@Entity //reiskia kad sitos klasess atstovai bus saugomi i DB
@Table(name = "contact")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
  //  @Column(name = "person_id")
  //  private Integer person_id;
    @Column(name = "contact")
    private String contact;
    @Column(name = "contact_type")
    private String type;
    
  //  @Transient
   // @OneToMany
    @ManyToOne(fetch = FetchType.LAZY) //ta puse kuri yra many to one ta dazniausia
         //!!!!!!!!!!!!yra siunciama (t.y. kai siunti adresa siunti ir persona kuriam jini priklauso)
    private Person person;

    public Contact() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer Id) {
        this.id = Id;
    }

//    public Integer getPerson_id() {
//        return person_id;
//    }
//
//    public void setPerson_id(Integer person_id) {
//        this.person_id = person_id;
//    }
//
//    
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Contact other = (Contact) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }    
    
    @Override
    public String toString() {
        return "Contact{" + "id=" + id 
            //    + ", person_id=" + person_id 
                + ", contact=" + contact + ", type=" + type + '}';
    }

   

}
