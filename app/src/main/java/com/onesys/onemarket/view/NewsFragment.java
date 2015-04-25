package com.onesys.onemarket.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.onesys.onemarket.MainActivity;
import com.onesys.onemarket.R;
import com.onesys.onemarket.adapter.NewsAdapter;
import com.onesys.onemarket.application.OneMarketApplication;
import com.onesys.onemarket.model.News;
import com.onesys.onemarket.model.ProductData;
import com.onesys.onemarket.task.LoadNewsDetailTask;
import com.onesys.onemarket.task.LoadNewsListTask;
import com.onesys.onemarket.utils.BaseFragment;

public class NewsFragment extends BaseFragment {

    private NewsAdapter newsAdapter;
    private ListView newsListView;

	public NewsFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_news_listview, container, false);

        newsAdapter = new NewsAdapter((MainActivity)getActivity());

        initNewsListView(rootView);
         
        return rootView;
    }

    private void initNewsListView(View view) {

        newsListView = (ListView) view.findViewById(R.id.lv_news);
        newsAdapter.clearData();

        newsListView.setAdapter(newsAdapter);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                //show product_detail view
                News news = (News) newsListView.getAdapter().getItem(position);
                showNewsDetailView(news);
            }
        });

        loadNewsList();
    }

    private void loadNewsList(){

        OneMarketApplication application = (OneMarketApplication) getActivity().getApplication();
        if (application.isOnline()) {
            new LoadNewsListTask(getActivity(), newsAdapter, null){
            }.execute();
        } else {
            Toast.makeText(this.getActivity(), " Network not available. Please check if you have enabled internet connectivity", Toast.LENGTH_LONG).show();
        }
    }

    private void showNewsDetailView(News news) {

        OneMarketApplication application = (OneMarketApplication) getActivity().getApplication();
        if (application.isOnline()) {
            new LoadNewsDetailTask((MainActivity) getActivity(), news.getId()).execute();
        } else {
            Toast.makeText(this.getActivity(), " Network not available. Please check if you have enabled internet connectivity", Toast.LENGTH_LONG).show();
        }
    }
}
