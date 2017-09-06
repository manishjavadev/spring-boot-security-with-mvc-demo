package com.manish.javadev.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.manish.javadev.model.RoleEntity;
import com.manish.javadev.model.UserEntity;
import com.manish.javadev.model.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	public UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = null;
		try {
			UserEntity userEntity = userRepository.findByUserName(username);

			System.out.println("userEntity = " + userEntity.getRoles());
			User user = new User(userEntity.getUserName(), userEntity.getPassword(), getAuthorities(userEntity));
			userDetails = (UserDetails) user;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userDetails;
	}

	private Set<GrantedAuthority> getAuthorities(UserEntity user) {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (RoleEntity role : user.getRoles()) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
			authorities.add(grantedAuthority);
			System.out.println("User Role " + role.getRole());
		}
		return authorities;
	}
}