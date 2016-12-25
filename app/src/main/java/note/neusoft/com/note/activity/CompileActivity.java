package note.neusoft.com.note.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnDismissListener;
import com.bigkoo.alertview.OnItemClickListener;
import com.bigkoo.pickerview.OptionsPickerView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import note.neusoft.com.note.R;

public class CompileActivity extends Activity implements OnDismissListener, OnItemClickListener {

    @ViewInject(R.id.tv_cacel)
    private TextView tv_cacel;
    @ViewInject(R.id.tv_finish)
    private TextView tv_finish;
    @ViewInject(R.id.rl_nickname)
    private RelativeLayout rl_nickname;
    @ViewInject(R.id.tv_nickname)
    private TextView tv_nickname;
    @ViewInject(R.id.rl_sex)
    private RelativeLayout rl_sex;
    @ViewInject(R.id.tv_sex)
    private TextView tv_sex;
    @ViewInject(R.id.rl_date)
    private RelativeLayout rl_date;
    @ViewInject(R.id.tv_date)
    private TextView tv_date;
    @ViewInject(R.id.rl_personnumber)
    private RelativeLayout rl_personnumber;
    @ViewInject(R.id.tv_personnumber)
    private TextView tv_personnumber;
    @ViewInject(R.id.rl_email)
    private RelativeLayout rl_email;
    @ViewInject(R.id.tv_email)
    private TextView tv_email;
    @ViewInject(R.id.et_signature)
    private EditText et_signature;

    OptionsPickerView optionsPickerView;
    private ArrayList<String> Sex;

    private AlertView mAlertViewExt_nickname,mAlertViewExt_personnumber,mAlertViewExt_Email;
    private EditText etName1,etName2,etName3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compile);
        ViewUtils.inject(this);
        InitData();
        Init();
    }

    @Override
    public void onBackPressed() {

        if(optionsPickerView.isShowing()){
            optionsPickerView.dismiss();
            tv_sex.setTextColor(Color.parseColor("#000000"));
        }else{
            finish();
            overridePendingTransition(R.anim.out_up_in,R.anim.out_down_out);
        }

    }

    private void InitData(){
        Sex=new ArrayList<>();
        Sex.add("男");
        Sex.add("女");


        optionsPickerView=new OptionsPickerView(this);
        optionsPickerView.setPicker(Sex);
        //三级联动效果
//        pvOptions.setPicker(options1Items, options2Items, options3Items, true);
//        //设置选择的三级单位
////        pwOptions.setLabels("省", "市", "区");
//        pvOptions.setTitle("选择城市");
//        pvOptions.setCyclic(false, true, true);
//        //设置默认选中的三级项目
//        //监听确定选择按钮
//        pvOptions.setSelectOptions(1, 1, 1);
//        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
//
//            @Override
//            public void onOptionsSelect(int options1, int option2, int options3) {
//                //返回的分别是三个级别的选中位置
//                String tx = options1Items.get(options1).getPickerViewText()
//                        + options2Items.get(options1).get(option2)
//                        + options3Items.get(options1).get(option2).get(options3).getPickerViewText();
//                tvOptions.setText(tx);
//                vMasker.setVisibility(View.GONE);
//            }
//        });

        optionsPickerView.setTitle("选择性别");
        optionsPickerView.setCyclic(false);
        optionsPickerView.setSelectOptions(1);
        optionsPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                String sex=Sex.get(options1);
                tv_sex.setText(sex);
                tv_sex.setTextColor(Color.parseColor("#000000"));
            }
        });



        mAlertViewExt_nickname = new AlertView("提示", "请输入您的昵称！", "取消", null, new String[]{"完成"}, this, AlertView.Style.Alert, this);
        mAlertViewExt_personnumber=new AlertView("提示", "请输入您的个人账号！", "取消", null, new String[]{"完成"}, this, AlertView.Style.Alert, this);
        mAlertViewExt_Email=new AlertView("提示", "请输入您的E-Mail地址！", "取消", null, new String[]{"完成"}, this, AlertView.Style.Alert, this);

        ViewGroup extView1= (ViewGroup) LayoutInflater.from(this).inflate(R.layout.alertext_form,null);
        etName1= (EditText) extView1.findViewById(R.id.etName);
        mAlertViewExt_nickname.addExtView(extView1);

        ViewGroup extView2= (ViewGroup) LayoutInflater.from(this).inflate(R.layout.alertext_form,null);
        etName2= (EditText) extView2.findViewById(R.id.etName);
        etName2.setHint("请您输入个人账号");
        mAlertViewExt_personnumber.addExtView(extView2);

        ViewGroup extView3= (ViewGroup) LayoutInflater.from(this).inflate(R.layout.alertext_form,null);
        etName3= (EditText) extView3.findViewById(R.id.etName);
        etName3.setHint("请输入您的E-Mail");
        mAlertViewExt_Email.addExtView(extView3);
    }

    private void Init(){

        tv_cacel.setOnClickListener(new View.OnClickListener() {//取消编辑
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.out_up_in,R.anim.out_down_out);
            }
        });
        tv_finish.setOnClickListener(new View.OnClickListener() {//保存编辑内容
            @Override
            public void onClick(View v) {

                //先保存，然后再退出
                /****************保存************************/


                /****************保存************************/
                finish();
                overridePendingTransition(R.anim.out_up_in,R.anim.out_down_out);
            }
        });

        rl_sex.setOnClickListener(new View.OnClickListener() {//点击进行性别选择
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
               tv_sex.setTextColor(Color.parseColor("#ff00ddff"));//对颜色进行转换
                optionsPickerView.show();
            }
        });

        rl_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        rl_nickname.setOnClickListener(new View.OnClickListener() {//点击昵称栏，弹出输入昵称的框
            @Override
            public void onClick(View v) {
                mAlertViewExt_nickname.show();
            }
        });
        rl_personnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertViewExt_personnumber.show();
            }
        });
        rl_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertViewExt_Email.show();
            }
        });

        rl_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){//监控/拦截/屏蔽返回键
            if(optionsPickerView.isShowing()){
                optionsPickerView.dismiss();
                tv_sex.setTextColor(Color.parseColor("#000000"));
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onDismiss(Object o) {

    }

    @Override
    public void onItemClick(Object o, int position) {

        if(o==mAlertViewExt_nickname&&position!=AlertView.CANCELPOSITION){
            tv_nickname.setText(etName1.getText().toString());
            etName1.setText("");
        }
        if(o==mAlertViewExt_Email&&position!=AlertView.CANCELPOSITION){
            tv_email.setText(etName2.getText().toString());
            etName2.setText("");
        }
        if(o==mAlertViewExt_personnumber&&position!=AlertView.CANCELPOSITION){
            tv_personnumber.setText(etName3.getText().toString());
            etName3.setText("");
        }
    }

    /**
     * 开启软键盘
     */
    private void openKeyboard(View view){
        InputMethodManager imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view,0);
    }

    /**
     * 关闭软键盘
     * @param view
     */
    private void closeKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromInputMethod(view.getWindowToken(),0);
    }
}