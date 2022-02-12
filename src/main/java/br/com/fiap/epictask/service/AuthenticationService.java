package br.com.fiap.epictask.service;

import br.com.fiap.epictask.model.User;
import br.com.fiap.epictask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


//Classe prórpria para autenticar o usuário
@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    //Pesquisa o usuario no banco de dados
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(username);
        if (user.isEmpty()) throw new UsernameNotFoundException("User not found");
        return user.get();
    }

    //Método para criptografar a senha do usuário
    public static PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
