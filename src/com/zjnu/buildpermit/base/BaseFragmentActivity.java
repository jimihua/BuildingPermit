/**   
 *    
 * 项目名称：TaurusClub     
 * 方法描述:   
 * 创建人：Administrator   
 * 创建时间：2015-3-12 下午3:34:24   
 * 修改人：Administrator   
 * 修改时间：2015-3-12 下午3:34:24   
 * 修改备注：   
 * @version    
 *    
 */
package com.zjnu.buildpermit.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.zjnu.buildpermit.common.AppManager;

/**
 * 方法描述:
 * 
 * @param
 * @author KIMI 创建时间：2015-3-12 下午3:34:24
 * @version
 * 
 */
public class BaseFragmentActivity extends FragmentActivity {
	BroadcastReceiver connectionReceiver = new BroadcastReceiver() {
		public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent) {

		}
	};

	private boolean isNerWorking = false;
	protected Context mContext;
	private View view;

	public boolean isNerWorking() {
		return this.isNerWorking;
	}

	/**
	 * 方法描述:
	 * 
	 * @param
	 * @author KIMI 创建时间：2015-3-12 下午3:34:54
	 * @version
	 * 
	 */
	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		mContext = this.getApplicationContext();
		AppManager.getAppManager().addActivity(this);
		requestWindowFeature(1);
		IntentFilter localIntentFilter = new IntentFilter();
		localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
		registerReceiver(this.connectionReceiver, localIntentFilter);
	}

	protected void onDestroy() {
		super.onDestroy();
		if (this.connectionReceiver != null)
			unregisterReceiver(this.connectionReceiver);
		AppManager.getAppManager().finishActivity(this);
	}
}
