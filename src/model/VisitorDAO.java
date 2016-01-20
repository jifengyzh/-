package model;

import databean.VisitorBean;

public class VisitorDAO {
	/**
	 * @overlode 原本genericDAO带了一个read primaryKey的功能，这里重写一下，需要read username
	 * @param username
	 * @return
	 */
	public VisitorBean read (String username) {
	}
	/**
	 * return all the customerBean
	 * @return
	 */
	public VisitorBean[] getCustomerList() {
		
	}
}
