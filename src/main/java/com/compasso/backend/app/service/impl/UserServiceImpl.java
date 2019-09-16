package com.compasso.backend.app.service.impl;

import com.compasso.backend.app.domain.entity.UserEntity;
import com.compasso.backend.app.exception.BusinessLogicException;
import com.compasso.backend.app.repository.IUserRepository;
import com.compasso.backend.app.service.IUserService;
import com.compasso.backend.app.util.ExpectThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUsername(username);
		if(ExpectThat.isNull(user)) {
			throw new UsernameNotFoundException(username);
		}
		return user;
	}

	@Override
	public UserEntity confirm(UserEntity userEntity) throws BusinessLogicException {
		try {
			return userRepository.save(userEntity);
		} catch (Exception e) {
			throw new BusinessLogicException(e);
		}
	}
	
}
