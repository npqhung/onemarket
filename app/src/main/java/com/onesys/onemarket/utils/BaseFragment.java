package com.onesys.onemarket.utils;

/**
 * Created by Hung on 11/03/2015.
 */

import android.app.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class BaseFragment extends Fragment
{
    private static final String TAG = "OneMarket";
    public int mIdScreen = -1;
    protected boolean mIsSelected = false;
    OnFragmentAttachedListener mListener = null;

    public void onAttach(Activity paramActivity)
    {
        Log.i(TAG,"BaseFragment - onAttach");
        super.onAttach(paramActivity);
        try
        {
            this.mListener = ((OnFragmentAttachedListener)paramActivity);
            return;
        }
        catch (ClassCastException localClassCastException)
        {
        }
        throw new ClassCastException(paramActivity.toString() + " must implement OnFragmentAttachedListener");
    }

    public void onBackPressed()
    {
    }

    public void onCreate(Bundle paramBundle)
    {
        Log.i(TAG,"BaseFragment - onCreate");
        super.onCreate(paramBundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG,"BaseFragment - onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void onFragmentSelected()
    {
        Log.i(TAG,"BaseFragment - onFragmentSelected");
        this.mIsSelected = true;
        reloadData();
    }

    public void onFragmentUnselected()
    {
        Log.i(TAG,"BaseFragment - onFragmentUnselected");
        this.mIsSelected = false;
    }

    public void onResume()
    {
        super.onResume();
        if (this.mListener != null)
            this.mListener.OnFragmentAttached();
    }

    public void onStart()
    {
        Log.i(TAG,"BaseFragment - onStart");
        super.onStart();
        if (this.mIsSelected)
            reloadData();
    }

    public void reloadData()
    {
    }

    public static abstract interface OnFragmentAttachedListener
    {
        public abstract void OnFragmentAttached();
    }

    public static abstract interface ShowContentListener
    {
        public abstract void showContent(int paramInt);
    }
}