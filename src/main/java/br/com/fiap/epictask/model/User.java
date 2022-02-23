package br.com.fiap.epictask.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;

@Data
@Entity(name = "TB_USER")
@SequenceGenerator(name = "user", sequenceName = "SQ_TB_USER", allocationSize = 1)
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "user", strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{user.name.blank}")
    private String name;

    @NotBlank(message = "{user.email.blank}")
    @Email(message = "{user.email.invalid}")
    private String email;

    @Size(min = 8, message = "{user.password.size}")
    private String password;

    private int points;


    public String getAvatarUrl() {
        return "https://avatars.githubusercontent.com/" + this.githubuser;
    }

    //Propriedades do UserDetails
    @NotBlank(message = "{user.github.blank}")
    private String githubuser;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
