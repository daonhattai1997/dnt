package dnt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dnt.entity.Audit.AuditUser;
import dnt.entity.EnumType.MenuTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "menu")
public class MenuProgram extends AuditUser {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id", unique = true, nullable = false)
    private int menuId;

    @NotBlank
    @Column(name = "menu_name", unique = true, length = 100)
    private String menuName;

    @NotBlank
    @Column(name = "parent_id", columnDefinition = "int default 0")
    private int parentId;

    @NotBlank
    @Column(name = "menu_url", length = 500)
    private String menuUrl;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private MenuTypeName menuType;

    @Column(name = "use_flag", nullable = false, columnDefinition = "varchar(3) default 'Y'")
    private String useFlag;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "role_menu",
            joinColumns = {
                    @JoinColumn(name = "menu_id", nullable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", nullable = false)
            })
    private List<Role> roles = new ArrayList<>();


}
