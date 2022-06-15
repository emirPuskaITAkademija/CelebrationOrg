package ba.organizuj.celebrationorg.ejb.celebration.entity;

import ba.organizuj.celebrationorg.ejb.user.entity.User;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "celebration")
@NamedQueries({
        @NamedQuery(name = "Celebration.findByUserCreator", query = "SELECT c FROM Celebration c WHERE c.userCreator=:userCreator")
})
public class Celebration implements Serializable {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @NotNull
    @Column(name = "celebrationDate")
    @Temporal(TemporalType.DATE)
    private Date celebrationDate;
    @JoinColumn(name = "user_creator", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userCreator;

    public Celebration() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCelebrationDate() {
        return celebrationDate;
    }

    public void setCelebrationDate(Date celebrationDate) {
        this.celebrationDate = celebrationDate;
    }

    public User getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(User userCreator) {
        this.userCreator = userCreator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Celebration that = (Celebration) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Celebration{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
