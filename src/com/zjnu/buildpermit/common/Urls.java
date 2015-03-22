/**
 * @author Kimi
 */
package com.zjnu.buildpermit.common;

/**
 * @author Kimi
 * 
 */
public class Urls {
	public final static String LOCALTION = "http://10.7.17.231";
	public final static String URL_NOTICE = LOCALTION + "/Notice/LoadDeclarejson";
	public final static String URL_EXPS = LOCALTION + "/Transport/LoadSignJson";
	public final static String URL_LOGIN = LOCALTION + "/Account/LogOn";
	/**
	 * 公告详情
	 * 
	 * @author Kimi
	 */
	public final static String URL_NOTICEDET = LOCALTION + "/Notice/DetailsBranch/";
	/**
	 * 提醒为签收
	 * 
	 * @author Kimi
	 */
	public final static String URL_REMINDUNSIGN = LOCALTION + "/Reminder/LoadUnSignTodayReminderJson";
	/**
	 * 提醒过期
	 * 
	 * @author Kimi
	 */
	public final static String URL_REMINDPAST = LOCALTION + "/Reminder/LoadPastTodayReminderJson";
	/**
	 * 提醒报名
	 * 
	 * @author Kimi
	 */
	public final static String URL_REMINDAPPLY = LOCALTION + "/Reminder/LoadApplyTodayReminderJson";
	/**
	 * 提醒缴费
	 * 
	 * @author Kimi
	 */
	public final static String URL_REMINDPAY = LOCALTION + "/Reminder/LoadPayTodayReminderJson";
	/**
	 * 提醒考试
	 * 
	 * @author Kimi
	 */
	public final static String URL_REMINDEXAM = LOCALTION + "/Reminder/LoadExamTodayReminderJson";
	/**
	 * 消息提醒
	 * 
	 * @author Kimi
	 */
	public final static String URL_GETISNEW = LOCALTION + "/Home/GetIsNew";

	public final static String URL_EXPSIGN = LOCALTION + "/Transport/SignPhone/";
	
	/**
	 * @author Kimi
	 * 项目审核数据
	 */
	public final static String URL_PRPJECTADMIN = LOCALTION + "/ProjectAdmin/LoadProjectAdminJson";
	/**
	 * @author Kimi
	 *  项目详情
	 */
	public final static String URL_PRPJECTADMINEDDETAIL = LOCALTION + "/ProjectAdmin/Details/";

}
