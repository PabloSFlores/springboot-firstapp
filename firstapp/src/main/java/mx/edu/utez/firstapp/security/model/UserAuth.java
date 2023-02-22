package mx.edu.utez.firstapp.security.model;

import mx.edu.utez.firstapp.models.person.Person;
import mx.edu.utez.firstapp.models.rol.Role;
import mx.edu.utez.firstapp.models.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserAuth implements UserDetails {
    private String username;
    private String password;
    private Person person;
    private Collection<? extends GrantedAuthority> authorities;

    public UserAuth(String username, String password, Person person,
                    Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.person = person;
        this.authorities = authorities;
    }

    public static UserAuth build(User user) {
        List<GrantedAuthority> authorities1 = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority((role.getName())))
                .collect(Collectors.toList());
        return new UserAuth(
                user.getUsername(),
                user.getPassword(),
                user.getPerson(),
                authorities1
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    public Person getPerson(){
        return person;
    }
    public void setPerson(Person person){
        this.person = person;
    }
}
