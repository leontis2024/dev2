package com.example.leontis.services;

import com.example.leontis.models.Usuario;
import com.example.leontis.repository.UsuarioRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepository usersRepository;

    public CustomUserDetailsService(UsuarioRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario users = usersRepository.findByEmailLikeIgnoreCase(username);

        return new org.springframework.security.core.userdetails.User(
                users.getEmail(),
                users.getSenha(),
                true,
                true,
                true,
                true,
                new ArrayList<>()
        );
    }
}
