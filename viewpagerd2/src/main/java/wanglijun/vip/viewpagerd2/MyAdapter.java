package wanglijun.vip.viewpagerd2;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * @author wlj
 * @date 2017/12/20
 * @packagename wanglijun.vip.viewpagerd2
 * 轮播图的适配器
 */
public class MyAdapter extends PagerAdapter {
    private Context context;
    private List<ImageView> mList;
    public MyAdapter(Context context, List<ImageView> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        return super.instantiateItem(container, position);
        position = position % mList.size();
        container.addView(mList.get(position));
        return mList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
