package com.zskjprojectj.andouclient.activity;

import android.graphics.Color;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.zskjprojectj.andouclient.R;
import com.zskjprojectj.andouclient.base.BaseActivity;
import com.zskjprojectj.andouclient.base.BasePresenter;
import com.zskjprojectj.andouclient.fragment.MeShopFragment;
import com.zskjprojectj.andouclient.fragment.MeShopforthegoodsFragment;
import com.zskjprojectj.andouclient.fragment.MeShoppaymentFragment;
import com.zskjprojectj.andouclient.fragment.MeShopreturngoodsFragment;
import com.zskjprojectj.andouclient.fragment.MeShopsendgoodsFragment;
import com.zskjprojectj.andouclient.fragment.MeShoptoevaluateFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 商城订单
 */
public class ShoporderActivity extends BaseActivity {
    private FixedIndicatorView indicator;
    //碎片集合
    private List<Fragment> list;
    private ViewPager viewPager;
    //第三方指示器
    private IndicatorViewPager indicatorViewPager;
    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_shoporder);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        topView.setTitle("商城订单");
    }

    @Override
    protected void initViews() {
        //这个FixedindicatorView是平分tab的屏幕长度的
        indicator = (FixedIndicatorView) findViewById(R.id.indicator);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        list = new ArrayList<Fragment>();
        Fragment meShopFragment=new MeShopFragment();
        list.add(meShopFragment);
        Fragment meShoppaymentFragment=new MeShoppaymentFragment();
        list.add(meShoppaymentFragment);
        Fragment meShopsendgoodsFragment=new MeShopsendgoodsFragment();
        list.add(meShopsendgoodsFragment);
        Fragment meShopforthegoodsFragment=new MeShopforthegoodsFragment();
        list.add(meShopforthegoodsFragment);
        Fragment meShoptoevaluateFragment=new MeShoptoevaluateFragment();
        list.add(meShoptoevaluateFragment);
        Fragment meShopreturngoodsFragment=new MeShopreturngoodsFragment();
        list.add(meShopreturngoodsFragment);
        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        indicatorViewPager.setAdapter(adapter);
        //设置滑动时的那一项的图形和颜色变化，ColorBar对应的是下划线的形状。
        indicator.setScrollBar(new ColorBar(getApplicationContext(), Color.parseColor("#00B2EE"), 5));
        viewPager.setOffscreenPageLimit(1);//缓存的左右页面的个数都是1
    }

    @Override
    public void getDataFromServer() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
    /**
     * 指示器适配器对形象
     */
    public IndicatorViewPager.IndicatorFragmentPagerAdapter adapter=new IndicatorViewPager.IndicatorFragmentPagerAdapter(getSupportFragmentManager())
    {
        private String[] tabNames = {"全部订单", "待付款","待发货","待收货","待评价","'退货/款"};
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            //此方法设置的tab的页面和显示
            if (convertView == null) {
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.tab,
                        container, false);
            }
            TextView tv = (TextView) convertView;
            tv.setText(tabNames[position]);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            //设置viewpager下的页面
            Fragment fragment = list.get(position);
            return fragment;
        }
    };
}
