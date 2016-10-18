package com.example.zhou.fragmentdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by zhou on 2016/10/17.
 */

public class MyListFragment extends ListFragment{
    public static final String TAG = "---->";
    private ClickListener listener;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnArticleSelectedListener");
        }
        Log.d(TAG,"onAttach1");

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate2");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView3");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG,"onActivityCreated4");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"onStart5");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume6");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"onPause7");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"onStop8");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG,"onDestroyView9");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy10");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG,"onDetach11");
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        listener.onTimeClick(position);
    }


    public static interface ClickListener{
        void onTimeClick(int position);
    }

}
