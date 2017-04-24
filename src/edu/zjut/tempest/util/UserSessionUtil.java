package edu.zjut.tempest.util;

import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

public class UserSessionUtil {
	
	public static void setUserSession(Object user) {
		HttpSession session =  ServletActionContext.getRequest().getSession();
//		if(user instanceof Teacher) {
//			System.out.println("ok");
//		}
		session.setAttribute("user", user);
	}
	
}
