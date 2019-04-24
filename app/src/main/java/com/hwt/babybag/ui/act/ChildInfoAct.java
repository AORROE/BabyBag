package com.hwt.babybag.ui.act;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hwt.babybag.MyApplication;
import com.hwt.babybag.R;
import com.hwt.babybag.bean.BaseEntity;
import com.hwt.babybag.bean.ChildInfoBean;
import com.hwt.babybag.network.RetrofitFactory;
import com.hwt.babybag.utils.MyDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ChildInfoAct extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.toolsbar_menu)
    public ImageView icon_menu;
    @BindView(R.id.tv_title_bar)
    public TextView tv_main_title;

    @BindView(R.id.child_avatar)
    public ImageView child_avatar;
    @BindView(R.id.child_nickname)
    public TextView child_name;
    @BindView(R.id.child_sex)
    public TextView child_sex;
    @BindView(R.id.child_birthday)
    public TextView child_birthday;
    @BindView(R.id.child_class)
    public TextView child_group_class;
    @BindView(R.id.child_instruction)
    public TextView child_instruction;
    @BindView(R.id.unbind_child)
    public Button unbind_child;
    private ChildInfoBean childInfo;
    private MyDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_info);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        tv_main_title.setText(intent.getStringExtra("childName"));
        setData(intent);
        icon_menu.setImageResource(R.drawable.icon_left_arrow);
        icon_menu.setOnClickListener(this);
        unbind_child.setOnClickListener(this);
    }

    /**
     * 设置孩子信息
     * @param intent
     */
    public void setData(Intent intent){
        childInfo = intent.getParcelableExtra("childInfo");
        child_name.setText(intent.getStringExtra("childName"));
        child_instruction.setText(childInfo.getCharacterInstructe());
        if(childInfo.getSex() == 0){
            child_sex.setText("男");
        }else {
            child_sex.setText("女");
        }
        if(childInfo.getPhoto() != null){
            Glide.with(ChildInfoAct.this).load(childInfo.getPhoto()).into(child_avatar);
        }else {
            child_avatar.setImageResource(R.drawable.icon_header);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolsbar_menu:
                setResult(0);
                finish();
                break;
            case R.id.unbind_child:
                dialog = new MyDialog(ChildInfoAct.this,"解绑了后，将删除该孩子的一切信息，是否继续",
                        "确定", "取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteChild();
                        dialog.dismiss();
                    }
                });
                dialog.setCannotBackPress();
                dialog.setCancelable(false);
                dialog.show();
                break;
        }
    }

    private void deleteChild(){
        RetrofitFactory.getRetrofiInstace().Api()
                .deleteChildById(childInfo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseEntity baseEntity) {
                        if (baseEntity.getStatus() == 1){
                            Intent intent = new Intent();
                            intent.setAction("action.refreshChilds");
                            sendBroadcast(intent);
                            setResult(0);
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
