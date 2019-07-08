/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Dell
 */
@Entity(name = "Role")
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_generator")
    @SequenceGenerator(name = "role_generator", sequenceName = "role_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(length = 64, name = "name", unique = true, nullable = false)
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

//    @ManyToMany(
//            fetch = FetchType.EAGER,
//            cascade = CascadeType.ALL
//    )
//    @JoinTable(
//            name = "roles_privileges",
//            joinColumns = @JoinColumn(
//                    name = "role_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(
//                    name = "privilege_id", referencedColumnName = "id"))
//    private Collection<Privilege> privileges = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    @JsonIgnore
    private Collection<Privilege> privileges;

    public Role(String name) {
        this.name = name;
    }

    public boolean hasPrivilege(Privilege privilege) {
        return privileges.contains(privilege);
    }
}
