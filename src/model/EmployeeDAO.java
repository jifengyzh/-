package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.EmployeeBean;
import databean.VisitorBean;

public class EmployeeDAO extends GenericDAO<EmployeeBean>{
	
	public EmployeeDAO(ConnectionPool cp, String tableName) throws DAOException, RollbackException {
		super(EmployeeBean.class, tableName, cp);
		EmployeeBean employeeBean = new EmployeeBean();
		employeeBean.setFirstName("admin");
		employeeBean.setLastName("admin");
		employeeBean.setUserName("admin");
		employeeBean.setPassword("123");
		create(employeeBean);
	}
	
	
	public EmployeeBean[] getAllEmployee() throws RollbackException {
		EmployeeBean[] EmployeeBeans = match();
		return EmployeeBeans;
	}
	
	public EmployeeBean read(String userName) throws RollbackException {
		EmployeeBean[] employee = match(MatchArg.equals("userName", userName));
		if (employee == null || employee.length == 0) return null;
		return employee[0];
	}

	public void setPassword(String userName, String password) throws RollbackException {
        try {
        	Transaction.begin();
        	EmployeeBean employee = read(userName);
			
			if (employee == null) {
				throw new RollbackException("Employee "+ userName +" does not exists");
			}
			
			employee.setPassword(password);
			update(employee);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
}
