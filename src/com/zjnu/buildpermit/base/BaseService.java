/**   
 *    
 * 项目名称：TaurusDemo     
 * 方法描述:   
 * 创建人：Administrator   
 * 创建时间：2014-12-18 下午4:55:07   
 * 修改人：Administrator   
 * 修改时间：2014-12-18 下午4:55:07   
 * 修改备注：   
 * @version    
 *    
 */
package com.zjnu.buildpermit.base;

import com.google.gson.Gson;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

/**
 * 方法描述:Service基类
 * 
 * @param
 * @author KIMI 创建时间：2014-12-18 下午4:55:07
 * @version
 * 
 */
public class BaseService extends Service {
	protected Context mContext;
	protected Gson gson;
	
	/**
	 * 方法描述:
	 * 
	 * @param
	 * @author KIMI 创建时间：2014-12-18 下午4:55:23
	 * @version
	 * 
	 */
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 方法描述:
	 * 
	 * @param
	 * @author KIMI 创建时间：2014-12-18 下午4:57:01
	 * @version
	 * 
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		gson = new Gson();
	}
}
