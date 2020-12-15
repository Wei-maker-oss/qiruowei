package com.example.zyday04;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zyday04.db.DaoSession;
import com.example.zyday04.db.UserBeanDao;

import java.util.List;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_insert;
    private Button btn_insert;
    private EditText et_deleat;
    private Button btn_deleat;
    private EditText et_update;
    private Button btn_update;
    private Button btn_query;
    private TextView tv_queryshow;
    private DaoSession mDaoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        et_insert = (EditText) findViewById(R.id.et_insert);
        btn_insert = (Button) findViewById(R.id.btn_insert);
        et_deleat = (EditText) findViewById(R.id.et_deleat);
        btn_deleat = (Button) findViewById(R.id.btn_deleat);
        et_update = (EditText) findViewById(R.id.et_update);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_query = (Button) findViewById(R.id.btn_query);
        tv_queryshow = (TextView) findViewById(R.id.tv_queryshow);

        btn_insert.setOnClickListener(this);
        btn_deleat.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_query.setOnClickListener(this);

        mDaoSession = App.getDaoSession();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert:
                intInsert();
                break;
            case R.id.btn_deleat:
                initDeleat();
                break;
            case R.id.btn_update:
                initUpdate();
                break;
            case R.id.btn_query:
                initQuery();
                break;
        }
    }

    // 插入数据
    private void intInsert() {
        UserBean userBean = new UserBean();
        // 获取对话框内容
       String str = et_update.getText().toString();
       if(str.contains(",")){
           String[] s = str.split(",");
           userBean.setId(Long.valueOf(s[0]));
           userBean.setName(s[1]);

           long insert = mDaoSession.getUserBeanDao().insert(userBean);
           if(insert>0){
               Log.e("TAG","插入成功");
           }
       }
    }

    // 删除数据
    private void initDeleat() {
        // 获取输入框内容
        final String ids = et_deleat.getText().toString();
        final List<UserBean> list =mDaoSession.getUserBeanDao()
                .queryBuilder()
                .where(UserBeanDao.Properties.Id.eq(ids))
                .list();
        // 遍历集合
//        for(UserBean u:list){
//
//        }

    }

    // 修改方法
    private void initUpdate() {
    }


    // 查询方法
    private void initQuery() {

//       mDaoSession.getUserBeanDao()
//               .queryBuilder()
//               .where(UserBeanDao.Properties.Id.eq())
//               .build()
//               .list();
         List<UserBean> userBeans = mDaoSession.getUserBeanDao().loadAll();
         // 遍历
        for(UserBean u:userBeans){
            tv_queryshow.setText(u.getId()+"-------"+u.getName());
        }

    }


}
