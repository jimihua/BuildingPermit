/**
 * 
 */
package com.zjnu.buildpermit.bean;

import java.io.Serializable;

/**
 * @author vivid 快递信息
 */
public class Express implements Serializable {
	/**
	 * @author Kimi
	 */
	private static final long serialVersionUID = 3052534270783526547L;
	private String CertificateId;
	private String Code;
	private String Name;
	private String Owner;
	private String CertificateInfo;
	private String Status;
	private String CurrentCompanyName;
	private String FormCompanyName;
	private String Sender;
	private String ToCompanyName;
	private String Receiver;
	private String IsReceived;
	private String IsBorrowed;
	private String IsStained;
	private String RecordId;
	private String TransportId;
	private String PostInformation;
	private String PostIsReceived;
	
	public String getCertificateId() {
		return CertificateId;
	}

	public void setCertificateId(String certificateId) {
		CertificateId = certificateId;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getOwner() {
		return Owner;
	}

	public void setOwner(String owner) {
		Owner = owner;
	}

	public String getCertificateInfo() {
		return CertificateInfo;
	}

	public void setCertificateInfo(String certificateInfo) {
		CertificateInfo = certificateInfo;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getCurrentCompanyName() {
		return CurrentCompanyName;
	}

	public void setCurrentCompanyName(String currentCompanyName) {
		CurrentCompanyName = currentCompanyName;
	}

	public String getFormCompanyName() {
		return FormCompanyName;
	}

	public void setFormCompanyName(String formCompanyName) {
		FormCompanyName = formCompanyName;
	}

	public String getSender() {
		return Sender;
	}

	public void setSender(String sender) {
		Sender = sender;
	}

	public String getToCompanyName() {
		return ToCompanyName;
	}

	public void setToCompanyName(String toCompanyName) {
		ToCompanyName = toCompanyName;
	}

	public String getReceiver() {
		return Receiver;
	}

	public void setReceiver(String receiver) {
		Receiver = receiver;
	}

	public String getIsReceived() {
		return IsReceived;
	}

	public void setIsReceived(String isReceived) {
		IsReceived = isReceived;
	}

	public String getIsBorrowed() {
		return IsBorrowed;
	}

	public void setIsBorrowed(String isBorrowed) {
		IsBorrowed = isBorrowed;
	}

	public String getIsStained() {
		return IsStained;
	}

	public void setIsStained(String isStained) {
		IsStained = isStained;
	}

	public String getRecordId() {
		return RecordId;
	}

	public void setRecordId(String recordId) {
		RecordId = recordId;
	}

	public String getTransportId() {
		return TransportId;
	}

	public void setTransportId(String transportId) {
		TransportId = transportId;
	}

	public String getPostInformation() {
		return PostInformation;
	}

	public void setPostInformation(String postInformation) {
		PostInformation = postInformation;
	}

	public String getPostIsReceived() {
		return PostIsReceived;
	}

	public void setPostIsReceived(String postIsReceived) {
		PostIsReceived = postIsReceived;
	}

}
