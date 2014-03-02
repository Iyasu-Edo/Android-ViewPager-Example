package com.michaelbarany.examples.viewpager;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class SimplePagerAdapter extends PagerAdapter {
    private final List<String> mPages = new ArrayList<>();
    private int totalViewsCreated = 0;
    private SimplePagerAdapterListener mListener = null;
    public interface SimplePagerAdapterListener {
        void onViewsInMemoryChanged(int totalViewsInMemory);
    }

    public void addPage(String page) {
        mPages.add(page);
    }

    @Override
    public int getCount() {
        return mPages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View view = inflater.inflate(R.layout.pager_item, container, false);
        ((TextView) view.findViewById(R.id.page)).setText(mPages.get(position));
        container.addView(view);
        totalViewsCreated++;
        notifyListener();
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        totalViewsCreated--;
        notifyListener();
    }

    public void setListener(SimplePagerAdapterListener activity) {
        mListener = activity;
    }

    private void notifyListener() {
        if (null == mListener) {
            return;
        }
        mListener.onViewsInMemoryChanged(totalViewsCreated);
    }
}
