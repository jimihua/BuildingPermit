package com.zjnu.buildpermit.service;

import java.lang.reflect.Field;

import net.tsz.afinal.http.AjaxCallBack;

import org.apache.http.Header;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.protocol.HttpContext;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import com.zjnu.buildpermit.R;
import com.zjnu.buildpermit.activity.BuildingActivity;
import com.zjnu.buildpermit.activity.LoginActivity;
import com.zjnu.buildpermit.activity.MainBuildingActivity;
import com.zjnu.buildpermit.base.BaseService;
import com.zjnu.buildpermit.bean.News;
import com.zjnu.buildpermit.common.CFinal;
import com.zjnu.buildpermit.common.LogUtils;
import com.zjnu.buildpermit.common.Urls;
import com.zjnu.buildpermit.utils.MyUtil;

/**
 * 方法描述: 服务器推送消息
 * 
 * @param
 * @author KIMI 创建时间：2014-12-14 上午11:07:29
 * @version
 * 
 */
@SuppressWarnings("all")
public class ActService extends BaseService {
	/** 创建参数 */
	boolean threadDisable = false;
	int count;
	NotificationManager notificationManager;
	private AsyncHttpClient asyncHttpClient;
	private PersistentCookieStore mCookieStore;

	public void handleMessage(Message msg) {
		// process incoming messages here
	};

	public IBinder onBind(Intent intent) {
		return null;
	}

	public void onCreate() {

		super.onCreate();
		mContext = this;

		notificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
		/** 创建一个线程，每秒计数器加一，并在控制台进行Log输出 */

		new Thread(new Runnable() {

			public void run() {

				while (true) {
					try {
						Thread.sleep(300 * 1000);
					} catch (InterruptedException e) {

					}
					CFinal.fh.post(Urls.URL_GETISNEW, new AjaxCallBack() {
						public void onFailure(Throwable t, int paramAnonymousInt, String str) {
							super.onFailure(t, paramAnonymousInt, str);
							LogUtils.d(t);
							if (str != null) {
								String msg = new String(str);
								System.out.println(1);
								if (msg.contains("head")) {
									System.out.println(2);
									RequestParams mParams = new RequestParams();
									mParams.put("UserName", CFinal.GetComXml(mContext, "username"));
									mParams.put("Password", CFinal.GetComXml(mContext, "password"));

									CFinal.getSyncHttpClient(mContext).post(Urls.URL_LOGIN, new AsyncHttpResponseHandler() {
										@Override
										public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
											// TODO Auto-generated method stub
											MyUtil.Log("msg", new String(arg2));
											System.out.println(3);
										}

										@Override
										public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
											// TODO Auto-generated method stub

											for (Cookie cookie : mCookieStore.getCookies()) {
												System.out.println(cookie.getName() + cookie.getValue());

											}
											CFinal.getSyncHttpClient(mContext).post(Urls.URL_GETISNEW, new AsyncHttpResponseHandler() {

												@Override
												public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
													// TODO Auto-generated
													// method
													// stub
													System.out.println(4);
												}

												@Override
												public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
													// TODO Auto-generated
													// method
													// stub
													String msg = new String(arg2);
													System.out.println(5);
													sendNews(msg);
												}

											});

										}

									});

								}
							}
						}

						public void onSuccess(Object object) {
							super.onSuccess(object);
							String msg = object.toString();
							sendNews(msg);
						}
					});

				}
			}
		}).start();
	}

	public void onDestroy() {
		super.onDestroy();
		/** 服务停止时，终止计数进程 */
		this.threadDisable = true;
	}

	public int getConunt() {
		return count;
	}

	class ServiceBinder extends Binder {
		public ActService getService() {
			return ActService.this;
		}
	}

	/**
	 * 发送消息的notice
	 * 
	 * @param msg
	 */
	private void sendNews(String msg) {
		News news = gson.fromJson(msg, News.class);
		Notification notification = NoticeHelper.genNotification(mContext, (int) System.currentTimeMillis(), R.drawable.icon_notice, "", 1, "今日消息提醒", getNewsMsg(news),
				BuildingActivity.class, 1);
		NoticeHelper.notify(mContext, (int) System.currentTimeMillis(), notification);

	}

	private String getNewsMsg(News news) {
		StringBuilder stringBuilder = new StringBuilder();
		Field[] fields = news.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				if (!field.get(news).equals("0")) {
					stringBuilder.append(MyUtil.getNews(field.getName())).append(field.get(news)).append(",");
				}
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return stringBuilder.toString();

	}

}
