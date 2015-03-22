/**
 * @author Kimi
 */
package com.zjnu.buildpermit.activity;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.zjnu.buildpermit.R;
import com.zjnu.buildpermit.adapter.ListViewExpDetAdapter;
import com.zjnu.buildpermit.base.BaseListActivity;
import com.zjnu.buildpermit.bean.Express;
import com.zjnu.buildpermit.common.CFinal;
import com.zjnu.buildpermit.common.Urls;
import com.zjnu.buildpermit.utils.MyUtil;

/**
 * @author Kimi
 * 
 */
@SuppressWarnings("all")
public class ExpressDetActivity extends BaseListActivity {
	private ArrayList<Express> mExpresses = new ArrayList<Express>();

	@ViewInject(id = R.id.pm_expdet_lv_qs)
	ListView mListView;
	@ViewInject(id = R.id.pm_expdet_btn_sign, click = "onClick")
	Button pm_expdet_btn_sign;
	@ViewInject(id = R.id.pm_expdet_btn_cancel, click = "onClick")
	Button pm_expdet_btn_cancel;
	@ViewInject(id = R.id.pm_expdet_tv_detail)
	TextView pm_expdet_tv_detail;
	@ViewInject(id = R.id.pm_expdet_et_signman)
	EditText pm_expdet_et_signman;

	private int position;
	private List<Express> expresses = new ArrayList<Express>();

	@SuppressWarnings("unchecked")
	protected void onCreate(Bundle paramBundle) {
		// TODO Auto-generated method stub
		super.onCreate(paramBundle);
		setContentView(R.layout.pm_exp_detail);
		Bundle bundle = getIntent().getBundleExtra("exp");
		mExpresses = (ArrayList<Express>) bundle.getSerializable("exp");
		for (Express mExpress : mExpresses) {
			if (mExpress.getTransportId().equals(CFinal.getPutMsg()[0])) {
				mExpress.setStatus("完好");
				expresses.add(mExpress);
				pm_expdet_tv_detail.setText(mExpress.getPostInformation());
			}
		}
		this.mAdapter = new ListViewExpDetAdapter<Express>(mContext, expresses, R.layout.pm_expdet_list_item);
		this.mListView.setAdapter(mAdapter);
		this.mListView.setDividerHeight(0);
		this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> mAdapterView, View view, int position, long paramAnonymousLong) {
				final String[] item = { "完好", "破损", "污染" };
				ExpressDetActivity.this.position = position;

				new AlertDialog.Builder(mContext).setTitle("请选择证书状态").setIcon(android.R.drawable.ic_dialog_info)
						.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
								expresses.get(ExpressDetActivity.this.position).setStatus(item[which]);
								ExpressDetActivity.this.mAdapter.notifyDataSetChanged();
							}
						}).show();

			}
		});
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.pm_expdet_btn_sign:
			AjaxParams mParams = new AjaxParams();
			mParams.put("TransportId", expresses.get(0).getTransportId());
			System.out.println(expresses.get(0).getTransportId());
			mParams.put("IsSelfSign", "False");
			mParams.put("SignPersonId", "123");
			mParams.put("OtherSigner", pm_expdet_et_signman.getText().toString());
			String str = "";
			for (int i = 0; i < expresses.size(); i++) {
				mParams.put("Records[" + i + "].RecordId", expresses.get(i).getRecordId());
				mParams.put("Records[" + i + "].IsStained", getStatus(expresses.get(i).getStatus()));

			}
			CFinal.fh.post(Urls.URL_EXPSIGN + expresses.get(0).getTransportId(), mParams, new AjaxCallBack() {
				public void onFailure(Throwable t, int paramAnonymousInt, String paramAnonymousString) {
					super.onFailure(t, paramAnonymousInt, paramAnonymousString);
					System.out.println(paramAnonymousString);
				}

				public void onSuccess(Object object) {
					super.onSuccess(object);

					MyUtil.toast(mContext, "签收成功");
					MyUtil.JumpPages(mContext, ExpressActivity.class, "signSuccess");
					ExpressDetActivity.this.finish();
				}

			});

			break;
		case R.id.pm_expdet_btn_cancel:
			this.finish();
			break;
		default:
			break;
		}
	}

	private String getStatus(String status) {

		if (status.equals("完好"))
			return "1";
		else if (status.equals("破损")) {
			return "2";
		} else if (status.equals("污染")) {
			return "3";
		}
		return "1";
	}
}
