package com.onesys.onemarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.onesys.onemarket.MainActivity;
import com.onesys.onemarket.R;
import com.onesys.onemarket.model.News;
import com.onesys.onemarket.utils.image.ImageLoader;

import java.util.ArrayList;

public class NewsAdapter extends BaseAdapter {
    private MainActivity context;

    private ArrayList<News> newsList;

    public ArrayList<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(ArrayList<News> newsList) {
        this.newsList = newsList;
    }

    public NewsAdapter(MainActivity c){
        context = c;
    }

    public NewsAdapter(MainActivity context, ArrayList<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @Override
    public int getCount() {
        if(newsList == null) {
            return 0;
        }

        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.layout_news_listview_item, null);

        ImageView img = (ImageView)convertView.findViewById(R.id.news_listitem_img);
        ImageLoader imgLoader = new ImageLoader(convertView.getContext());
        imgLoader.DisplayImage(newsList.get(position).getCover(), img);

        WebView titleWebView = (WebView)convertView.findViewById(R.id.news_listitem_title);
        titleWebView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent) {
//                if (paramAnonymousMotionEvent.getAction() == 0)
//                    TinAdapter.this.mListener.showMore(paramInt);
                return false;
            }
        });

        titleWebView.loadDataWithBaseURL("x-data://base", newsList.get(position).getTitle(), "text/html", "UTF-8", null);

        TextView tv_time = (TextView)convertView.findViewById(R.id.news_listitem_time);
        tv_time.setText((newsList.get(position)).getCreatedDate());

        convertView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
//                TinAdapter.this.mListener.showMore(paramInt);
            }
        });

        return convertView;
    }

    public void clearData()
    {
        if (this.newsList == null)
            this.newsList = new ArrayList();
        this.newsList.clear();
        notifyDataSetChanged();
    }

    public void addData(ArrayList<News> productList)
    {
        if (this.newsList == null)
            this.newsList = new ArrayList();

        this.newsList.addAll(productList);
        notifyDataSetChanged();
    }
}
