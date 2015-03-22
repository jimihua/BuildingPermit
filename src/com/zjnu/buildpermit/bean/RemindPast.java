/**
 * @author Kimi
 */
package com.zjnu.buildpermit.bean;

/**
 * @author Kimi
 * 提醒过期bean
 */
public class RemindPast {
	private String MessageType;
	private String ReminderId;
	private String CompanyName;
	private String CompanyOwner;
	private String CompanyPhone;
	private String CertificateName;
	private String CertificateCode;
	private String CertificateOwner;
	private String CertificateUrl;
	private String CertificateMD5;
	private String CertificateExtension;
	private String MessageType1;
	private String Remark;
	private String BeginRemindTime;

	public String getMessageType() {
		return MessageType;
	}

	public void setMessageType(String messageType) {
		MessageType = messageType;
	}

	public String getReminderId() {
		return ReminderId;
	}

	public void setReminderId(String reminderId) {
		ReminderId = reminderId;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	public String getCompanyOwner() {
		return CompanyOwner;
	}

	public void setCompanyOwner(String companyOwner) {
		CompanyOwner = companyOwner;
	}

	public String getCompanyPhone() {
		return CompanyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		CompanyPhone = companyPhone;
	}

	public String getCertificateName() {
		return CertificateName;
	}

	public void setCertificateName(String certificateName) {
		CertificateName = certificateName;
	}

	public String getCertificateCode() {
		return CertificateCode;
	}

	public void setCertificateCode(String certificateCode) {
		CertificateCode = certificateCode;
	}

	public String getCertificateOwner() {
		return CertificateOwner;
	}

	public void setCertificateOwner(String certificateOwner) {
		CertificateOwner = certificateOwner;
	}

	public String getCertificateUrl() {
		return CertificateUrl;
	}

	public void setCertificateUrl(String certificateUrl) {
		CertificateUrl = certificateUrl;
	}

	public String getCertificateMD5() {
		return CertificateMD5;
	}

	public void setCertificateMD5(String certificateMD5) {
		CertificateMD5 = certificateMD5;
	}

	public String getCertificateExtension() {
		return CertificateExtension;
	}

	public void setCertificateExtension(String certificateExtension) {
		CertificateExtension = certificateExtension;
	}

	public String getMessageType1() {
		return MessageType1;
	}

	public void setMessageType1(String messageType1) {
		MessageType1 = messageType1;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getBeginRemindTime() {
		return BeginRemindTime;
	}

	public void setBeginRemindTime(String beginRemindTime) {
		BeginRemindTime = beginRemindTime;
	}


	public String getDueTime() {
		return DueTime;
	}

	public void setDueTime(String dueTime) {
		DueTime = dueTime;
	}

	public String getInfo() {
		return Info;
	}

	public void setInfo(String info) {
		Info = info;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}


	private String DueTime;
	private String Info;
	private String Url;
}
