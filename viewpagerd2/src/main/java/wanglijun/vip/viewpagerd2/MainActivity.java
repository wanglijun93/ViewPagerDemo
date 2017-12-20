package wanglijun.vip.viewpagerd2;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个viewpager轮播图的demo
 * 2017/12/20
 *
 * @author wlj
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private LinearLayout mPointGroup;
    private int[] mImages = {R.mipmap.banner1, R.mipmap.banner2, R.mipmap.banner3, R.mipmap.banner4};
    private List<ImageView> mList;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.viewpager2);
        mPointGroup = (LinearLayout) findViewById(R.id.pointgroup);
        initImage();
        mViewPager.setAdapter(new MyAdapter(this, mList));
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            int lastPosition;

            @Override
            public void onPageSelected(int position) {
                //页面被选中，修改position
                position = position % mList.size();
                //设置当前页面选中
                mPointGroup.getChildAt(position).setSelected(true);
                //设置前一页不选中
                mPointGroup.getChildAt(lastPosition).setSelected(false);
                //替换位置
                lastPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
/**
 * 定时发送消息，实现自动循环轮播图
 */
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int currentPosition = mViewPager.getCurrentItem();
                if (currentPosition == mViewPager.getAdapter().getCount() - 1) {
                    //最后一页
                    mViewPager.setCurrentItem(0);
                } else {
                    mViewPager.setCurrentItem(currentPosition + 1);
                }
                //一直给自己发消息
                mHandler.postDelayed(this, 3000);
            }
        }, 5000);
    }

    /**
     * 准备显示的图片集合
     */
    private void initImage() {
        mList = new ArrayList<>();
        for (int i = 0; i < mImages.length; i++) {
            ImageView imageView = new ImageView(this);
            //将图片设置到imageview控件上
            imageView.setImageResource(mImages[i]);
            //将imageview控件添加到集合
            mList.add(imageView);
            //制作底部的小圆点
            ImageView pointImage = new ImageView(this);
            pointImage.setImageResource(R.drawable.shape_point_selector);
            //设置小圆点的布局参数
            int PointSize = getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(PointSize, PointSize);
            if (i > 0) {
                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
                pointImage.setSelected(false);
            } else {
                pointImage.setSelected(true);

            }
            pointImage.setLayoutParams(params);
            mPointGroup.addView(pointImage);
        }
    }
}
