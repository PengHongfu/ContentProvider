package com.peng.viewpager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewpager;
    private PagerAdapter mAdapter;
    private List<View> mViews =new ArrayList<>();

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
    }

    private void initEvent() {
        rb_click();
        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int cuttentItem =mViewpager.getCurrentItem();
                switch (cuttentItem) {
                    /*int index = 1;
                     object element = FindName(string.Concat("RadioButton", index));
                     if (element != null && element.GetType() == typeof(RadioButton))
                      ((RadioButton)element).IsChecked = true;*/
                    case 0:
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

    private void rb_click() {
        //点击事件
        mainRg = (RadioGroup) findViewById(R.id.main_rg);

        mainDb = (RadioButton) findViewById(R.id.main_db);
        mainFx = (RadioButton) findViewById(R.id.main_fx);
        mainQd = (RadioButton) findViewById(R.id.main_qd);
        mainMe = (RadioButton) findViewById(R.id.main_me);

        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.main_db :
                        mViewpager.setCurrentItem(0);
                        break;
                    case R.id.main_fx :
                        mViewpager.setCurrentItem(1);
                        break;
                    case R.id.main_qd :
                        mViewpager.setCurrentItem(2);
                        break;
                    case R.id.main_me :
                        mViewpager.setCurrentItem(3);
                        break;
                    default:
                    break;
                }
            }
        });
    }

    private void initView() {

        /**加载是个布局
         * 找到各种控件资源
         */

        mViewpager = (ViewPager) findViewById(R.id.id_viewpager);

        LayoutInflater mInflater = LayoutInflater.from(this);

        View tab01 = mInflater.inflate(R.layout.tab01,null);
        View tab02 = mInflater.inflate(R.layout.tab02,null);
        View tab03 = mInflater.inflate(R.layout.tab03,null);
        View tab04 = mInflater.inflate(R.layout.tab04,null);

        mViews.add(tab01);
        mViews.add(tab02);
        mViews.add(tab03);
        mViews.add(tab04);

        mAdapter = new PagerAdapter() {
            @Override
            public void destroyItem(ViewGroup container, int position, Object object)
            {
                container.removeView(mViews.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position)
            {
                View view = mViews.get(position);
                container.addView(view);
                return view;
            }
            @Override
            public int getCount() {
                return mViews.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        };
        mViewpager.setAdapter(mAdapter);
    }
}
