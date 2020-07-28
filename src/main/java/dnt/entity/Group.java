package dnt.entity;

import dnt.entity.Audit.AuditDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the _group database table.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "_group")
@NamedQuery(name = "Group.findAll", query = "SELECT g FROM Group g where delete_flag = 'N'")
public class Group extends AuditDate {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", unique = true, nullable = false)
    private int groupId;

    @Column(name = "group_name", nullable = false, length = 30)
    private String groupName;

    //bi-directional many-to-one association to Staff
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", nullable = false)
    private Staff manager;

    //bi-directional many-to-many association to Staff
    @ManyToMany
    @JoinTable(
            name = "group_staff",
            joinColumns = {
                    @JoinColumn(name = "group_id", nullable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "staff_id", nullable = false)
            })
    private List<Staff> staffs;

}