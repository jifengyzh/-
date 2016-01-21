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
	
	public EmployeeDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(EmployeeBean.class, tableName, cp);
	}
	
	
	public EmployeeBean[] getAllEmployee() throws RollbackException {
		EmployeeBean[] EmployeeBeans = match();
		return EmployeeBeans;
	}
	
	public EmployeeBean readEmployee(String userName) throws RollbackException {
		EmployeeBean[] employee = match(MatchArg.equals("userName", userName));
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
