package com.example.zhou.fragmentdemo;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements MyListFragment.ClickListener{
    //


    private FragmentManager manager;
    private FragmentTransaction trans;
    private LinearLayout detail;
    private MyListFragment titleList;
    private List<Map<String,String>> mapList;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        detail = (LinearLayout)findViewById(R.id.detail);
    }

    private void initData() {
        manager = getSupportFragmentManager();
        titleList = (MyListFragment) manager.findFragmentById(R.id.title_list);
        mapList = getSimpleData();
        titleList.setListAdapter(new SimpleAdapter(MainActivity.this,mapList,R.layout.item_title,new String[]{"title"},new int[]{R.id.title}));
        fragments = getFragments();
    }

    private void initEvent() {
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentB fb = new FragmentB();
//                trans.add(R.id.contain,fb,"hah");
//                trans.addToBackStack(null);
//                trans.commit();
//            }
//        });
    }

    private List<Map<String, String>> getSimpleData() {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for(int i=0;i<18;i++){
            Map<String, String> map = new HashMap<String, String>();
            map.put("title", "item"+i);
            list.add(map);
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("title", "跳转");
        list.add(map);
        return list;
    }

    private List<Fragment> getFragments() {
        List<Fragment> list = new ArrayList<Fragment>();
        list.add(new FragmentA());
        list.add(new FragmentB());
        list.add(new FragmentC());
        list.add(new FragmentD());
        return list;
    }

    @Override
    public void onTimeClick(int position) {
        if(position<=fragments.size()-1){
            trans =  manager.beginTransaction();//每次都要新开启一个事务  否则再次commit 会提示错误的状态异常!
            trans.replace(R.id.detail,fragments.get(position));
            trans.addToBackStack("a");
            trans.commit();
        }else if(position == mapList.size()-1){
            manager.popBackStack();

        }else if(position == mapList.size()-2){
            Intent intent = new Intent(this,TestActivtiy.class);
            startActivity(intent);
        }else if(position == mapList.size()-3){
            TestDialogFragment dialogFragment = new TestDialogFragment();
            dialogFragment.show(manager,"123");
        }else{
            Bundle b=new Bundle();
            b.putString("title",mapList.get(position).get("title"));
            FragmentOther other = new FragmentOther();
            other.setArguments(b);
            trans =  manager.beginTransaction();//每次都要新开启一个事务  否则再次commit 会提示错误的状态异常!
            trans.replace(R.id.detail,other);
            trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            trans.commit();
//            manager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//                @Override
//                public void onBackStackChanged() {
//
//                }
//            });
//            manager.popBackStack();
//            Toast.makeText(this,mapList.get(position).get("title"),Toast.LENGTH_LONG).show();
        }
    }
}
