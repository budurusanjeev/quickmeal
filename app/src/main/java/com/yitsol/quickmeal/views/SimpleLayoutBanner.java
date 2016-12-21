package com.yitsol.quickmeal.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.banner.widget.Banner.base.BaseBanner;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yitsol.quickmeal.R;
import com.yitsol.quickmeal.domain.RestaurantDomain;
import com.yitsol.quickmeal.listner.ListenerforRestaurentBanner;


public class SimpleLayoutBanner extends BaseBanner<RestaurantDomain, SimpleLayoutBanner> {

    public ListenerforRestaurentBanner indicator;

    Viewholder viewholder;
    private DisplayImageOptions options;


    public SimpleLayoutBanner(Context context) {
        this(context, null, 0);
    }


    public SimpleLayoutBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleLayoutBanner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onTitleSlect(TextView tv, int position) {
    }

    @Override
    public View onCreateItemView(final int position) {
        final View opview = View.inflate(context, R.layout.banner_restaurent, null);
        final RestaurantDomain restaurantDomain = list.get(position);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.loading_image)
                .showImageForEmptyUri(R.mipmap.loading_image)
                .showImageOnFail(R.mipmap.loading_image)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
        viewholder = new Viewholder();

        viewholder.btn_order = (Button) opview.findViewById(R.id.btn_order);

        viewholder.image_bg = (ImageView) opview.findViewById(R.id.image_bg);
        viewholder.ll_restaurent = (LinearLayout) opview.findViewById(R.id.ll_restaurent);
        viewholder.tv_restaurent_theme = (TextView) opview.findViewById(R.id.tv_restaurent_theme);
        viewholder.tv_restaurent_name = (TextView) opview.findViewById(R.id.tv_restaurent_name);


        viewholder.tv_restaurent_name.setText(restaurantDomain.restaurantName);
        viewholder.tv_restaurent_theme.setText(restaurantDomain.restaurantMoto);

        ImageLoader.getInstance().displayImage(list.get(position).restaurantImageUrl, viewholder.image_bg, options);


        //new FontStyle(context).applyfontToGroup(viewholder.ll_restaurent);
        viewholder.btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indicator.getViewingObject(restaurantDomain);
            }
        });


        return opview;
    }

    public void SetOnBannerClickListener(ListenerforRestaurentBanner indicator) {
        this.indicator = indicator;
    }

//    private RoundCornerIndicaor indicator;

    @Override
    public View onCreateIndicator() {
//        indicator = new RoundCornerIndicaor(context);
//        indicator.setViewPager(vp, list.size());
//        return indicator;
        return null;
    }

    @Override
    public void setCurrentIndicator(int i) {
        //  indicator.setCurrentItem(i);
    }

    class Viewholder {
        TextView tv_restaurent_theme, tv_restaurent_name;
        Button btn_order;
        LinearLayout ll_restaurent;
        ImageView image_bg;
    }

}
