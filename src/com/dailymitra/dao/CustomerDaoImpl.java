package com.dailymitra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dailymitra.dao.util.DbUtil;
import com.dailymitra.model.Customer;

public class CustomerDaoImpl implements CustomerDao {

	private static final String SAVE_CUSTOMER_QUERY = "INSERT INTO DM_CUSTOMER VALUES(?, ?, ?, ?)";

	@Override
	public int saveCustomer(Customer customer) {
		try (Connection con = DbUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(SAVE_CUSTOMER_QUERY)) {
			pstmt.setString(1, customer.getUserName());
			pstmt.setString(2, customer.getName());
			pstmt.setString(3, customer.getEmail());
			pstmt.setString(4, customer.getMobileNumber());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
