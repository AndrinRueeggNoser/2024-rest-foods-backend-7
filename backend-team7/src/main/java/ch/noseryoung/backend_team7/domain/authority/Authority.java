package ch.noseryoung.backend_team7.domain.authority;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "authority")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private Integer authorityId;

    @Column(name = "authority_name")
    private String authorityName;
}