package com.cxp.mrrd.base;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.CompoundButton;

import com.cxp.mrrd.R;
import com.cxp.mrrd.ui.widget.dialog.ShapeLoadingDialog;
import com.cxp.mrrd.utils.AppUtils;
import com.cxp.mrrd.utils.T;
import com.cxp.mrrd.view.MvpView;

/**
 * 文 件 名: BaseFragment
 * 创 建 人: CXP
 * 创建日期: 2017-01-21 6:57
 * 描    述: Fragment 父类
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class BaseFragment extends Fragment implements MvpView {


    private ShapeLoadingDialog shapeLoadingDialog;
    private Handler handler = new Handler();

    //显示加载弹层
    protected void showDialog(){
        //检测网络
        if (AppUtils.isNoNet(getContext())) {
            return;
        }
        shapeLoadingDialog=new ShapeLoadingDialog(getContext());
        shapeLoadingDialog.show();
        //15秒自动关闭
        handler.postDelayed(run, 30000);
    }

    private Runnable run=new Runnable() {
        @Override
        public void run() {
            if (shapeLoadingDialog.isShow()) {
                shapeLoadingDialog.dismiss();
                T.show(getContext(), getString(R.string.net_error));
            }
        }
    };

    //隐藏加载弹层
    protected void dissDialog() {
        shapeLoadingDialog.dismiss();
        if (handler != null) {
            handler.removeCallbacks(run);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (handler!=null) {
            handler.removeCallbacks(run);
        }
    }



    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked, int position) {

    }
}
