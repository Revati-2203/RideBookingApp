package com.project.bookRide.app.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.bookRide.app.entities.User;
import com.project.bookRide.app.exceptions.ResourceNotFoundException;
import com.project.bookRide.app.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{
	
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public User getUserById(Long userId) {
		return userRepository.findById(userId)
							.orElseThrow(()-> 
								new ResourceNotFoundException("User not found with id :"+ userId));
		};

}
