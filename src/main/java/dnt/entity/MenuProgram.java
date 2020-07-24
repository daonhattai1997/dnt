package dnt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dnt.entity.Audit.AuditUser;
import dnt.entity.EnumType.MenuTypeName;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "menu")
public class MenuProgram extends AuditUser {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id", unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private int menuId;

    @NotBlank
    @Column(name = "menu_name", unique = true, length = 100)
    private String menuName;

    @NotBlank
    @Column(name = "menu_file", unique = true)
    private String menuFile;

    @NotBlank
    @Column(name = "menu_url", length = 500)
    private String menuUrl;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private MenuTypeName menuType;

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
