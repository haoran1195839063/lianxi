package haoran.bwie.com.lianxi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import haoran.bwie.com.lianxi.adapter.MyAdapter;
import haoran.bwie.com.lianxi.bean.MyBean;
import haoran.bwie.com.lianxi.presenter.presenter;
import haoran.bwie.com.lianxi.view.ViewImpl;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewImpl {

    private EditText edit;
    private Button search;
    private RecyclerView recy;
    private String string;
    private List<MyBean.DataBean> list = new ArrayList<>();
    private haoran.bwie.com.lianxi.presenter.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        initView();
        //调用p层
        presenter = new presenter(this);
        //定义hashmap
        HashMap<String, String> params = new HashMap<>();
        params.put("keywords", "笔记本");
        params.put("page", "1");
        //调用里面的方法
        presenter.login(params);
    }

    private void initView() {
        edit = (EditText) findViewById(R.id.edit);
        search = (Button) findViewById(R.id.search);
        recy = (RecyclerView) findViewById(R.id.recy);
        //给recyclerviewl设置显示样式
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recy.setLayoutManager(linearLayoutManager);
        //查找点击事件
        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search:
                recy.removeAllViews();
                string = edit.getText().toString();
                HashMap<String, String> params = new HashMap<>();
                params.put("keywords", string);
                params.put("page", "1");
                presenter.login(params);
                break;
        }
    }

    private void submit() {
        // validate
        String editString = edit.getText().toString().trim();
        if (TextUtils.isEmpty(editString)) {
            Toast.makeText(this, "请输入商品", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    @Override
    public void success(MyBean mybean) {
        list.addAll(mybean.getData());
        MyAdapter myAdapter = new MyAdapter(this, list);
        recy.setAdapter(myAdapter);
    }

    @Override
    public void error(String string) {
        Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show();
    }
}
