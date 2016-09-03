package com.bihu.xiyi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.bihu.xiyi.R;
import com.bihu.xiyi.entity.XiYiJi;

import java.util.ArrayList;

/**
 * Created by luo on 2016/8/24.
 */
public class XiYiJiAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<XiYiJi> list;
    private ImageLoader imageLoader;
    public XiYiJiAdapter(ArrayList<XiYiJi> list, Context context,ImageLoader imageLoader) {
        this.list = list;
        this.context = context;
        this.imageLoader = imageLoader;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final XiYiJi xiyiji = list.get(i);
        View linearLayout =null;
        ViewHolder viewHolder=null;
        if(view==null){
            viewHolder=new ViewHolder();
            linearLayout =  View.inflate(context, R.layout.item_xiyiji, null);
            viewHolder.floortv= (TextView) linearLayout.findViewById(R.id.floor_view);
            viewHolder.idtv= (TextView) linearLayout.findViewById(R.id.id_view);
            viewHolder.imageview= (ImageView) linearLayout.findViewById(R.id.im_view);
            viewHolder.check_Box= (CheckBox) linearLayout.findViewById(R.id.check_Box);
            linearLayout.setTag(viewHolder);
        }else {
            linearLayout=view;
            viewHolder= (ViewHolder) linearLayout.getTag();
        }
        viewHolder.check_Box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                xiyiji.setCheck(b);
            }
        });
       viewHolder.check_Box.setChecked(xiyiji.isCheck());
        viewHolder.floortv.setText(xiyiji.getFloor());
        viewHolder.idtv.setText(xiyiji.getObjectId());
        String url=xiyiji.getImg();
        ImageLoader.ImageListener imageListener=ImageLoader.getImageListener(viewHolder.imageview,R.drawable.xiyiji,R.drawable.xiyiji);
        ImageLoader.ImageContainer imageContainer=imageLoader.get(url,imageListener,96,96);
        return linearLayout;
    }
    public static class ViewHolder{
        private TextView floortv;
        private TextView idtv;
        private  ImageView imageview;
        private CheckBox check_Box;
    }
    public void add(ArrayList<XiYiJi> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }
}
