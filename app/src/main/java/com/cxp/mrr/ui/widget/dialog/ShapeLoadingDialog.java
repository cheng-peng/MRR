package com.cxp.mrr.ui.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.cxp.mrr.R;


/**
 * 文 件 名: ShapeLoadingDialog
 * 创 建 人: CXP
 * 创建日期: 2017-02-24 16:37
 * 描    述: 加载弹窗
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class ShapeLoadingDialog {

    private Context mContext;
    private Dialog mDialog;
    private ProgressBar mLoadingView;
    private View mDialogContentView;


    public ShapeLoadingDialog(Context context) {
        this.mContext=context;
        init();
    }

    private void init() {
        mDialog = new Dialog(mContext, R.style.custom_dialog);
        mDialogContentView= LayoutInflater.from(mContext).inflate(R.layout.layout_dialog,null);
        mDialog.setCanceledOnTouchOutside(false);
        mLoadingView= (ProgressBar) mDialogContentView.findViewById(R.id.loadView);
        mDialog.setContentView(mDialogContentView);
    }

    public void setBackground(int color){
        GradientDrawable gradientDrawable= (GradientDrawable) mDialogContentView.getBackground();
        gradientDrawable.setColor(color);
    }

    public void setLoadingText(CharSequence charSequence){
//        mLoadingView.setLoadingText(charSequence);
    }

    public void show(){
        mDialog.show();
    }
    public boolean isShow(){
       return mDialog.isShowing();
    }

    public void dismiss(){
        mDialog.dismiss();
    }

    public Dialog getDialog(){
        return  mDialog;
    }

    public void setCanceledOnTouchOutside(boolean cancel){
        mDialog.setCanceledOnTouchOutside(cancel);
    }
}
