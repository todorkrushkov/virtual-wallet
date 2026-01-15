package krushkov.project.com.virtualwallet.models;

import jakarta.persistence.*;
import krushkov.project.com.virtualwallet.models.enums.RoleType;
import lombok.*;

@Entity
@Table(name = "roles")

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "role_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true, length = 10)
    private RoleType name = RoleType.ROLE_USER;
}
