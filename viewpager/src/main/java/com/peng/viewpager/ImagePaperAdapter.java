package com.peng.viewpager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class ImagePaperAdapter extends PagerAdapter {
    private ArrayList<ImageView> list;

    public ImagePaperAdapter(ArrayList<ImageView> list) {
        // TODO Auto-generated constructor stub
        this.list = list;
    }

    @Override
    public int getCount() {
        // 获取当前窗体界面数
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        //用于判断是否由对象生成界面
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        //Warning：不要在这里调用removeView  
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
       //return一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中
        ImageView view = list.get(position);
        ViewParent vp = view.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(view);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.mainActivity,"你点击的是"+position,Toast.LENGTH_SHORT).show();
            }
        });
        //上面这些语句必须加上，如果不加的话，就会产生则当用户滑到第四个的时候就会触发这个异常
        //原因是我们试图把一个有父组件的View添加到另一个组件。
        ((ViewPager) container).addView(list.get(position));

        System.out.println("------------");
        return list.get(position);
    }

}