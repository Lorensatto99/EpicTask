package br.com.fiap.epictask.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity(name = "TB_ROLE")
@Table(name = "TB_ROLE")
public class Role implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "user", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name; //ROLE_ADMIN ou ROLE_USER


    @Override
    public String getAuthority() {
        return this.name;
    }
}
