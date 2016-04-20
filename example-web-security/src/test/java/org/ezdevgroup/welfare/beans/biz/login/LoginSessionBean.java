package org.ezdevgroup.welfare.beans.biz.login;

import java.io.Serializable;

import org.ezdevgroup.welfare.beans.basic.EzClientBean;
import org.ezdevgroup.welfare.beans.basic.EzUserBean;

public class LoginSessionBean implements Serializable {
	private static final long	serialVersionUID	= -6930518341835322232L;
	private EzUserBean		userBean	= null;
	private EzClientBean	clientBean	= null;

	public EzUserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(EzUserBean userBean) {
		this.userBean = userBean;
	}

	public EzClientBean getClientBean() {
		return clientBean;
	}

	public void setClientBean(EzClientBean clientBean) {
		this.clientBean = clientBean;
	}
}
