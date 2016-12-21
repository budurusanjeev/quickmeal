package com.yitsol.quickmeal.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yitsol.quickmeal.R;
import com.yitsol.quickmeal.domain.RestaurantDomain;
import com.yitsol.quickmeal.listner.ListenerforRestaurentBanner;

import java.util.ArrayList;


/**
 * Created by Nikita on 12-Apr-16.
 */
public class RestaurantAdapter extends BaseAdapter {

    ArrayList<RestaurantDomain> restaurantDomains;

    Context context;
    ListenerforRestaurentBanner listenerforRestaurentBanner;
    LayoutInflater layoutInflater;
    private DisplayImageOptions options;

    public RestaurantAdapter(Context context, ArrayList<RestaurantDomain> restaurantDomains) {
        this.context = context;

        this.restaurantDomains = restaurantDomains;

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.loading_image)
                .showImageForEmptyUri(R.mipmap.loading_image)
                .showImageOnFail(R.mipmap.loading_image)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();

    }

    @Override
    public int getCount() {
        if (restaurantDomains != null && restaurantDomains.size() > 0)
            return restaurantDomains.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        return restaurantDomains.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final RestaurantDomain restaurantDomain = (RestaurantDomain) getItem(position);
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.row_restaurents, null);

            viewHolder.img_go = (ImageView) convertView.findViewById(R.id.img_go);

            viewHolder.img_go.setVisibility(View.GONE);

            viewHolder.img_restaurant = (ImageView) convertView.findViewById(R.id.img_restaurant);

            viewHolder.tv_restaurent_area = (TextView) convertView.findViewById(R.id.tv_restaurent_area);

            viewHolder.tv_restaurent_name = (TextView) convertView.findViewById(R.id.tv_restaurent_name);

            viewHolder.ll_row_restaurent_parent = (LinearLayout) convertView.findViewById(R.id.ll_row_restaurent_parent);

            ImageLoader.getInstance().displayImage(restaurantDomain.restaurantImageUrl, viewHolder.img_restaurant, options);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // new FontStyle(context).applyfontToGroup(viewHolder.ll_row_restaurent_parent);

        viewHolder.tv_restaurent_name.setText(restaurantDomain.restaurantName);

        viewHolder.tv_restaurent_area.setText(restaurantDomain.restaurantMoto);

        viewHolder.img_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerforRestaurentBanner.getViewingObject(restaurantDomain);
            }
        });

        return convertView;
    }

    public void listenerRestaurent(@Nullable ListenerforRestaurentBanner listenerforRestaurentBanner) {
        this.listenerforRestaurentBanner = listenerforRestaurentBanner;
    }

    class ViewHolder {
        ImageView img_restaurant, img_go;

        TextView tv_restaurent_name, tv_restaurent_area;

        LinearLayout ll_row_restaurent_parent;
    }
}
