package com.masai.service;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.LoginException;
import com.masai.model.CurrentLoginSession;
import com.masai.model.Customer;
import com.masai.model.LoginDto;
import com.masai.model.LoginType;
import com.masai.repository.CustomerRepo;
import com.masai.repository.SessionRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private SessionRepo sesRepo;

	@Autowired
	private CustomerRepo cusRepo;

	@Override
	public String loginAccount(LoginDto dto) throws LoginException {
		// TODO Auto-generated method stub

		System.out.println(LoginType.CUSTOMER);

		System.out.println(dto);

		Customer customer = cusRepo.findByEmail(dto.getEmail());

		if (customer == null) {
			throw new LoginException("Please enter Valid email....");
		}

		Optional<CurrentLoginSession> currentloginsession = sesRepo.findById(customer.getCustomerId());

		if (currentloginsession.isPresent()) {
			throw new LoginException("Customer Already Logged in....");
		}

		if (customer.getPassword().equals(dto.getPassword())) {

			String key = RandomString.make(6);

			CurrentLoginSession currloginsession2 = new CurrentLoginSession(customer.getCustomerId(), key,
					LocalDateTime.now());

			CurrentLoginSession cls = sesRepo.save(currloginsession2);

			return cls.toString();

		} else {
			throw new LoginException("please Enter valid password...");
		}

	}

	@Override
	public String LogOutFromAccount(String key) throws LoginException {
		// TODO Auto-generated method stub

		CurrentLoginSession currentloginsession = sesRepo.findByUuid(key);

		if (currentloginsession == null) {
			throw new LoginException("user not Logged In with this number");
		}

		sesRepo.delete(currentloginsession);

		return "logged Out!";
	}

}
