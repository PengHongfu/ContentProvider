package com.peng.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.peng.viewpager.fragment.Discover_Fragment;
import com.peng.viewpager.fragment.Doubao_Fragment;
import com.peng.viewpager.fragment.List_Fragment;
import com.peng.viewpager.fragment.Me_Fragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private ViewPager mViewpager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragmentViews = new ArrayList<>();
    private RadioGroup mainRg;
    private RadioButton mainDb;
    private RadioButton mainFx;
    private RadioButton mainQd;
    private RadioButton mainMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
        rb_click();
    }

    /**
     * Viewpager滑动响应事件,底部的按钮更换图片
     */
    private void initEvent() {
        /**
         *Viewpager滑动的监听事件
         */
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                int cuttentItem = mViewpager.getCurrentItem();
                switch (cuttentItem) {
                    case 0:
                        /**
                         * 图片在selected_ic_home定义选中和未选择事件
                         * android:drawableTop="@drawable/selected_ic_home"
                         * android:checked="true"显示
                         */
                        mainDb.setChecked(true);
                        break;
                    case 1:
                        mainFx.setChecked(true);
                        break;
                    case 2:
                        mainQd.setChecked(true);
                        break;
                    case 3:
                        mainMe.setChecked(true);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 底部四个按钮的点击事件
     */
    private void rb_click() {
        /**
         * RadioGroup里包含4个RadioButton
         * 通过RadioGroup的setOnCheckedChangeListener方法检测点击的按钮
         */
        mainRg = (RadioGroup) findViewById(R.id.main_rg);
        /**
         * 4个RadioButton按钮
         */
        mainDb = (RadioButton) findViewById(R.id.main_db);
        mainFx = (RadioButton) findViewById(R.id.main_fx);
        mainQd = (RadioButton) findViewById(R.id.main_qd);
        mainMe = (RadioButton) findViewById(R.id.main_me);
        /**
         * 监听事件
         */
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                /**
                 * 默认从0开始
                 * 按照上面定义4个按钮的方法顺序从0开始
                 */
                switch (checkedId) {
                    case R.id.main_db:
                        mViewpager.setCurrentItem(0);
                        break;
                    case R.id.main_fx:
                        mViewpager.setCurrentItem(1);
                        break;
                    case R.id.main_qd:
                        mViewpager.setCurrentItem(2);
                        break;
                    case R.id.main_me:
                        mViewpager.setCurrentItem(3);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 把四个布局加载到MainActivity中,形成滑动效果
     */
    private void initView() {

        /**加载四个布局
         * 找到各种控件资源
         */
        mViewpager = (ViewPager) findViewById(R.id.id_viewpager);
        /**
         * 得到Fragment对象
         */
        Fragment mtab01 = new Doubao_Fragment();
        Fragment mtab02 = new Discover_Fragment();
        Fragment mtab03 = new List_Fragment();
        Fragment mtab04 = new Me_Fragment();

        /**
         * 把得到的Fragment对象 加到List<Fragment> 集合当中
         */
        mFragmentViews.add(mtab01);
        mFragmentViews.add(mtab02);
        mFragmentViews.add(mtab03);
        mFragmentViews.add(mtab04);

        /**
         * 适配器,通过new FragmentPagerAdapter 对象的方法 下面的注释是另一种方法
         * 把得到的fragment 通过适配器 ,显示在viewpager上
         * mViewpager.setAdapter(mAdapter);
         */
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return mFragmentViews.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentViews.size();
            }
        };
        mViewpager.setAdapter(mAdapter);

        /* 适配器,通过继承FragmentPagerAdapter的方法
        mViewpager.setAdapter(new myadapter(getSupportFragmentManager()));
    private  class myadapter extends FragmentPagerAdapter{

        public myadapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentViews.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentViews.size();
        }
    }*/
    }

}
