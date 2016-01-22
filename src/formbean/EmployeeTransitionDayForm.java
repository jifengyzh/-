package formbean;

import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBean;

import databean.FundBean;
import model.FundDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;


public class EmployeeTransitionDayForm extends FormBean {

	private String   date;
	private String[] fundId;
	private String[] closingPrice;
	private int[]    fundIdInt;
	private long[]   price; 
	private String   button;
	
	public String    getDate()                   { return date;           }	
	public String[]  getFundId()			     { return fundId;	      }
	public String[]  getClosingPrice()           { return closingPrice;   }
	public int[]     getFundIdInt()              { return fundIdInt;      }
	public long[]    getPirce()                  { return price;          }
	public String    getButton()                 { return button;         }
	
	public void      setDate(String s)           { date = s;              }
	public void      setFundId(String[] s) 	     { fundId = s;            }
	public void      setClosingPrice(String[] s) { closingPrice = s;      }
	public void      setFundIdInt(int[] s)       { fundIdInt = s;         }
	public void      setPrice(long[] s)          { price = s;             }
	public void      setButton(String s)         { button = s;            } 
	
	
	public EmployeeTransitionDayForm(HttpServletRequest request) {
		date = trimAndConvert(request.getParameter("specifiedDate"), "<>\"");
		button = trimAndConvert(request.getParameter("button"), "<>\"");
		
		fundId = request.getParameterValues("fundId");
		if (fundId != null) {
			for (int i = 0; i < fundId.length; i++) {
				fundId[i] = trimAndConvert(fundId[i], "<>\"");
			}
		}
		
		closingPrice = request.getParameterValues("price");
		if (closingPrice != null) {
			for (int i = 0; i < closingPrice.length; i++) {
				closingPrice[i] = trimAndConvert(closingPrice[i], "<>\"");
			}
		}
	}
	
	public boolean isPresent() {
		return button != null && button.equals("Submit");
	}
	
	public boolean isFundListEmpty() {
		return fundId == null || closingPrice == null;
	}
	
	public Date getSpecifiedDate() {
		try {
			return new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH).parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public int[] getFundIdsAsInteger() {
		fundIdInt = new int[fundId.length];
		for (int i = 0; i < fundId.length; i++) {
			fundIdInt[i] = Integer.parseInt(fundId[i]);
		}
		return fundIdInt;
	}
	
	public long[] getClosingPricesAsDouble() {
		price = new long[closingPrice.length];
		for (int i = 0; i < closingPrice.length; i++) {
			price[i] = Long.parseLong(closingPrice[i]);
		}
		return price;
	}
	
	public HashMap<Integer, Long> getNewFundPrice() {
		// TODO Auto-generated method stub
		HashMap<Integer, Long> fundmap = new HashMap<Integer, Long>(); 
		for (int i = 0; i < fundId.length; i++) {
			fundmap.put(fundIdInt[i], price[i]);
		}
		
		return fundmap;
	}
		
		
	//		date = trimAndConvert(request.getParameter("specifiedDate"), "<>\"");
//		button = trimAndConvert(request.getParameter("button"), "<>\"");
//		
//		fundId = request.getParameterValues("fundId");
//		if (fundId != null) {
//			for (int i = 0; i < fundId.length; i++) {
//				fundId[i] = trimAndConvert(fundId[i], "<>\"");
//			}
//		}
//		
//		closingPrice = request.getParameterValues("price");
//		if (closingPrice != null) {
//			for (int i = 0; i < closingPrice.length; i++) {
//				closingPrice[i] = trimAndConvert(closingPrice[i], "<>\"");
//			}
//		}
	
	

	public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (date == null || date.length() == 0) errors.add("Date is required");
        if (button == null) errors.add("Button is required");

        if (errors.size() > 0) return errors;

        if (!button.equals("Submit")) errors.add("Invalid button in Transition Day");
        
        try {
			new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH).parse(date);
		} catch (ParseException e) {
			errors.add("Date input should follow the format \"MM-dd-yyyy\".");
		}
        
        if (isFundListEmpty()) return errors;
        
        for (int i = 0; i < fundId.length; i++) {
        	int count = i + 1;
        	try {
    			Integer.parseInt(fundId[i]);
    		} catch (NumberFormatException e) {
    			errors.add("The " + count + "th Fund Id is not an integer");
    		}
        	
        	try {
        		double temp = Double.parseDouble(closingPrice[i]);
    			if (temp < 0.01 || temp > Math.pow(10, 4)) 
    				throw new IllegalArgumentException("The " + count + "th closing price is not an number between 0.01 dollar and 10,000.00 dollars");
    		} catch (NumberFormatException e) {
    			errors.add("The " + count + "th closing price is not an valid number.");
    		} catch (IllegalArgumentException e2) {
    			errors.add(e2.getMessage());
    		}
        }
		
        return errors;
    }
	
	public String trimAndConvert(String s, String charsToConvert) {
		if (s == null) return null;
		if (!s.matches(".*["+charsToConvert+"].*")) {
			return s.trim();
		}
		
		StringBuffer b = new StringBuffer();
		for (char c : s.trim().toCharArray()) {
			switch (c) {
				case '<':
					if (charsToConvert.indexOf('<') != -1) {
						b.append("&lt;");
					} else {
						b.append(c);
					}
					break;
				case '>':
					if (charsToConvert.indexOf('>') != -1) {
						b.append("&gt;");
					} else {
						b.append(c);
					}
					break;
				case '&':
					if (charsToConvert.indexOf('&') != -1) {
						b.append("&amp;");
					} else {
						b.append(c);
					}
					break;
				case '"':
					if (charsToConvert.indexOf('"') != -1) {
						b.append("&quot;");
					} else {
						b.append(c);
					}
					break;
				default:
					if (charsToConvert.indexOf(c) != -1) {
						b.append("&#"+c+";");
					} else {
						b.append(c);
					}
			}
		}
		
		return b.toString();
	}
}
