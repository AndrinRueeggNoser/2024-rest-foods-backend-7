package ch.noseryoung.backend_team7.domain.role;

import ch.noseryoung.backend_team7.domain.authority.Authority;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_authorities",
            joinColumns = {
                    @JoinColumn(name = "id_role", referencedColumnName = "role_id"),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "id_authority", referencedColumnName = "authority_id")
            }
    )
    private Set<Authority> roleAuth;

}
