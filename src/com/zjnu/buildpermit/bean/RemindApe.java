/**
 * @author Kimi
 */
package com.zjnu.buildpermit.bean;

/**
 * @author Kimi
 * 提醒 申请,支付和考试共同的bean
 */

public class RemindApe {
	 private String MessageType;
     private String ReminderId;
     private String MessageType1;
     private String Remark;
     private String BeginRemindTime;
     private String DueTime;
     private String Info;
     private String Url;
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
}
