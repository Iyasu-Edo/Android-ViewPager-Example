package com.michaelbarany.examples.viewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

public class MainActivity extends Activity implements SimplePagerAdapter.SimplePagerAdapterListener {

    private TextView mTextView;
    private ViewPager mViewPager;
    private SimplePagerAdapter mSimplePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textview);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mSimplePagerAdapter = new SimplePagerAdapter();
        mSimplePagerAdapter.setListener(this);
        for (int i = 0; i < 10; i++) {
            mSimplePagerAdapter.addPage("Page " + i);
        }
        mViewPager.setAdapter(mSimplePagerAdapter);
    }

    @Override
    public void onViewsInMemoryChanged(int totalViewsInMemory) {
        if (null == mTextView || null == mViewPager) {
            return;
        }
        int total = mSimplePagerAdapter.getCount();
        int current = mViewPager.getCurrentItem();
        int offscreenLimit = mViewPager.getOffscreenPageLimit();
        int left = current < offscreenLimit ? current : offscreenLimit;
        int right = total - 1 - current < offscreenLimit ? total - 1 - current : offscreenLimit;
        mTextView.setText(
            "Left Pages: " + left + " / " +
            "Active Pages: " + totalViewsInMemory + " / " +
            "Right Pages: " + right + " / " +
            "OffscreenPageLimit: " + offscreenLimit
        );
    }

}
