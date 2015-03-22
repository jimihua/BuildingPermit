/**
 * @author Kimi
 */
package com.zjnu.buildpermit.bean;


/**
 * @author Kimi
 *
 */
public class Notice {
	
	private String Title;
	private String IsRed;
	private String CreateTime;
	private String NoticeId;
	private String IsReaded;
	
	
	public String getIsReaded() {
		return IsReaded;
	}
	public void setIsReaded(String isReaded) {
		IsReaded = isReaded;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getIsRed() {
		return IsRed;
	}
	public void setIsRed(String isRed) {
		IsRed = isRed;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getNoticeId() {
		return NoticeId;
	}
	public void setNoticeId(String noticeId) {
		NoticeId = noticeId;
	}
	
}
