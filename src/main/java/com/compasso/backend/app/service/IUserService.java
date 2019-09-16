package com.compasso.backend.app.service;

import com.compasso.backend.app.domain.entity.UserEntity;
import com.compasso.backend.app.exception.BusinessLogicException;
import com.compasso.backend.app.pattern.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IService, UserDetailsService {

	UserEntity confirm(UserEntity userEntity) throws BusinessLogicException;
	
}
