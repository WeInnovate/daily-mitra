package com.dailymitra.service;

import com.dailymitra.dao.CustomerDao;
import com.dailymitra.dao.CustomerDaoImpl;
import com.dailymitra.model.Customer;

public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDao customerDao;
	
	public CustomerServiceImpl() {
		customerDao = new CustomerDaoImpl();
	}

	@Override
	public int saveCustomer(Customer customer) {
		return customerDao.saveCustomer(customer);
	}

}
