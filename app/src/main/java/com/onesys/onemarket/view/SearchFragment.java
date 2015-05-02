package com.onesys.onemarket.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.onesys.onemarket.MainActivity;
import com.onesys.onemarket.R;
import com.onesys.onemarket.adapter.ProductAdapter;
import com.onesys.onemarket.application.OneMarketApplication;
import com.onesys.onemarket.task.LoadPhoneProductTask;
import com.onesys.onemarket.task.LoadSearchTask;
import com.onesys.onemarket.utils.BaseFragment;
import com.onesys.onemarket.utils.Constants;
import com.onesys.onemarket.utils.response.ProductListResponse;

import org.apache.http.message.BasicNameValuePair;

import java.util.LinkedList;

public class SearchFragment extends BaseFragment
        implements TextWatcher, TextView.OnEditorActionListener {

    EditText editSearchContent;

    MainActivity context;

    public SearchFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        context = (MainActivity) getActivity();

        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        editSearchContent = ((EditText) rootView.findViewById(R.id.search_content));
        editSearchContent.addTextChangedListener(this);
        editSearchContent.setOnEditorActionListener(this);
         
        return rootView;
    }

    private void hideSoftKeyboard() {
        ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.editSearchContent.getWindowToken(), 0);
    }

    public void afterTextChanged(Editable paramEditable) {
    }

    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {
    }

    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {
    }

    public boolean onEditorAction(TextView paramTextView, int paramInt, KeyEvent paramKeyEvent) {
        boolean bool = false;
//        if (paramInt == 3)
//        {
        hideSoftKeyboard();
        searchAction(this.editSearchContent.getText().toString());
        bool = true;
//        }
        return bool;
    }

    public void searchAction(String paramString) {
        hideSoftKeyboard();
        if (TextUtils.isEmpty(paramString)) {
            return;
        }

        loadData(paramString);
    }

    private void loadData(String searchStr) {
        OneMarketApplication application = (OneMarketApplication) getActivity().getApplication();

        final LinkedList criteria = new LinkedList();
        criteria.add(new BasicNameValuePair("key_search", searchStr));

        ProductAdapter productAdapter = new ProductAdapter((MainActivity) getActivity());

        FragmentManager fragmentManager = context.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.KEY_SEARCH_CRITERIA, criteria);

        SearchResultFragment searchResultFragment = new SearchResultFragment();
        searchResultFragment.setArguments(bundle);

        fragmentTransaction.add(R.id.frame_container, (Fragment) searchResultFragment, "" + MainActivity.SEARCH_RESULT_VIEW);
        if (!context.mListScreen.contains(searchResultFragment)) {
            context.mListScreen.add(searchResultFragment);
        }

        context.hideAllFragment(fragmentTransaction);
        fragmentTransaction.show(searchResultFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
