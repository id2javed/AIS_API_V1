package com.app.main.model;

import java.io.InputStream;

public class LoginResponse {
	
	private String loginId;
	private String userName;
	private String loginName;
	private String roleId;
	private String ownersId;
	private String createdBy;
	private String key;
	private String encryptKey;
	private String logoFilePath;
	private String themeColour;
	private InputStream logoFile;
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getOwnersId() {
		return ownersId;
	}
	public void setOwnersId(String ownersId) {
		this.ownersId = ownersId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getEncryptKey() {
		return encryptKey;
	}
	public void setEncryptKey(String encryptKey) {
		this.encryptKey = encryptKey;
	}
	public String getLogoFilePath() {
		return logoFilePath;
	}
	public void setLogoFilePath(String logoFilePath) {
		this.logoFilePath = logoFilePath;
	}
	public String getThemeColour() {
		return themeColour;
	}
	public void setThemeColour(String themeColour) {
		this.themeColour = themeColour;
	}
	public InputStream getLogoFile() {
		return logoFile;
	}
	public void setLogoFile(InputStream logoFile) {
		this.logoFile = logoFile;
	}
	
}
