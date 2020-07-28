
package dnt.entity;

import dnt.entity.Audit.AuditDate;
import lombok.*;

import javax.persistence.*;


/**
 * The persistent class for the _user database table.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "_user")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u where delete_flag = 'N'")
public class User extends AuditDate {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_name", unique = true, nullable = false, length = 30)
    private String username;

    @Column(nullable = false)
    private String password;

    //bi-directional many-to-one association to Staff
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setToStaff(Staff staff) {
        setStaff(staff);
        staff.setAccount(this);
    }

    public void removeFromStaff(Staff staff) {
        setStaff(null);
        staff.setAccount(null);
    }

}