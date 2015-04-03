package com.onesys.onemarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.onesys.onemarket.MainActivity;
import com.onesys.onemarket.R;
import com.onesys.onemarket.model.ProductComment;
import com.onesys.onemarket.utils.image.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Hung on 12/03/2015.
 */
public class CommentAdapter extends BaseAdapter {
    private MainActivity context;

    private ArrayList<ProductComment> commentList;

    public ArrayList<ProductComment> getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList<ProductComment> commentList) {
        this.commentList = commentList;
    }

    public CommentAdapter(MainActivity c){
        context = c;
    }

    public CommentAdapter(MainActivity context, ArrayList<ProductComment> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @Override
    public int getCount() {
        if(commentList == null) {
            return 0;
        }

        return commentList.size();
    }

    @Override
    public Object getItem(int position) {
        return commentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null){
            view = inflater.inflate(R.layout.layout_productdetail_comment_item, null);
        }

        ProductComment comment = commentList.get(position);

        ImageView localImageView = (ImageView)view.findViewById(R.id.iv_comment_user_image);
        TextView userName = (TextView)view.findViewById(R.id.tv_comment_user_name);
        TextView tvComment = (TextView)view.findViewById(R.id.tv_comment_user_comment);
        userName.setText(comment.getFullname());
        tvComment.setText(comment.getContent());
        ImageLoader imgLoader = new ImageLoader(view.getContext());
        imgLoader.DisplayImage(comment.getAvatar(), localImageView);

        return view;

    }

    public void clearData()
    {
        if (this.commentList == null)
            this.commentList = new ArrayList();
        this.commentList.clear();
        notifyDataSetChanged();
    }

    public void addData(ArrayList<ProductComment> commentList)
    {
        if (this.commentList == null)
            this.commentList = new ArrayList();
        this.commentList.clear();
        this.commentList.addAll(commentList);
        notifyDataSetChanged();
    }
}
