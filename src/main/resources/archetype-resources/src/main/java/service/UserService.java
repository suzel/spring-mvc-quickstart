package ${package}.service;

import java.util.Collections;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;

import ${package}.repository.UserRepository;

public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	protected void initialize() {
		userRepository.save(new ${package}.domain.User("user", "demo", "ROLE_USER"));
		userRepository.save(new ${package}.domain.User("admin", "admin", "ROLE_ADMIN"));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		${package}.domain.User user = userRepository.findByEmail(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		return createUser(user);
	}
	
	public void signin(${package}.domain.User user) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(user));
	}
	
	private Authentication authenticate(${package}.domain.User user) {
		return new UsernamePasswordAuthenticationToken(createUser(user), null, Collections.singleton(createAuthority(user)));		
	}
	
	private User createUser(${package}.domain.User user) {
		return new User(user.getEmail(), user.getPassword(), Collections.singleton(createAuthority(user)));
	}

	private GrantedAuthority createAuthority(${package}.domain.User user) {
		return new SimpleGrantedAuthority(user.getRole());
	}

}