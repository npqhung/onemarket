package com.onesys.onemarket.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.onesys.onemarket.MainActivity;
import com.onesys.onemarket.R;
import com.onesys.onemarket.application.OneMarketApplication;
import com.onesys.onemarket.model.CartItem;
import com.onesys.onemarket.utils.BaseFragment;
import com.onesys.onemarket.utils.Utils;
import com.onesys.onemarket.utils.image.ImageLoader;


public class CartFragment extends BaseFragment implements View.OnClickListener{

    private static final String TAG = "OneMarket";

    private LinearLayout llCartList;
	public CartFragment(){}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        Log.i(TAG,"CartFragment - onCreateView");
        //super.onCreateView(inflater,container,savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_cart, container, false);
        
        initCartView(rootView);
         
        return rootView;
    }

    public void onAttach(Activity paramActivity){
        Log.i(TAG,"CartFragment - onAttach");
        super.onAttach(paramActivity);
        this.mIsSelected = true;
    }

    public void initCartView(View view){
        
        ImageView rightMenu = ((ImageView)view.findViewById(R.id.top_menu_right_cart));
        rightMenu.setOnClickListener(this);

        ImageView backBtn = ((ImageView)view.findViewById(R.id.top_menu_left_cart));
        backBtn.setOnClickListener(this);

        TextView tvPayment = ((TextView)view.findViewById(R.id.tv_cart_payment));
        tvPayment.setOnClickListener(this);

        llCartList = (LinearLayout)view.findViewById(R.id.cart_listViewCart);
        
    }
    public void onClick(View paramView) {
        switch (paramView.getId()) {
            default:
                return;
            case 2131558457:
//                showNaviCall();
//                return;
            case R.id.top_menu_left_cart:
                //show Home
                ((MainActivity)getActivity()).mFootMenu.showHome();
                return;

            case R.id.tv_cart_payment:
                OneMarketApplication application = (OneMarketApplication)getActivity().getApplication();
                if (application.getDataCart() == null || application.getDataCart().getCartItems().size() == 0)
                {
                    Toast.makeText(getActivity(), "No data to charge", Toast.LENGTH_SHORT).show();
                    return;
                }
//                String str1 = application.getEmail();
                String str2 = application.getUserId();
                if ((//TextUtils.isEmpty(str1)) ||
                        TextUtils.isEmpty(application.getUserId())))
                {
                    Toast.makeText(getActivity(), "You must update account info to do it", Toast.LENGTH_SHORT).show();
                    return;
                }
                ((MainActivity)getActivity()).showContent(MainActivity.USER_INFO_CONFIRMATION_VIEW);


                break;
        }
    }

    public void reloadData(){
        Log.i(TAG,"CartFragment - reloadData ");
        loadData();
    }

    private void loadData()
    {

        Log.i(TAG,"CartFragment - loadData ");
        llCartList.removeAllViews();

        OneMarketApplication application = (OneMarketApplication)getActivity().getApplication();
        LayoutInflater localLayoutInflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i = 0; ; i++)
        {
            if (i >= application.getDataCart().getCartItems().size())
            {
                updateValue();
                return;
            }
            final int j = i;
            final View cartItemView = localLayoutInflater.inflate(R.layout.layout_listview_cart_item, null);
            final ImageView ivCartImg = (ImageView)cartItemView.findViewById(R.id.iv_cartitem_product_img);

            TextView tvName = (TextView)cartItemView.findViewById(R.id.tv_cartitem_product_name);

            TextView tvPrice = (TextView)cartItemView.findViewById(R.id.tv_cartitem_price);
            final TextView tvColor = (TextView)cartItemView.findViewById(R.id.tv_cartitem_color);
            TextView tvQuantity = (TextView)cartItemView.findViewById(R.id.tv_cartitem_quantity);

            ((ImageView)cartItemView.findViewById(R.id.iv_cartitem_trash)).setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View paramAnonymousView)
                {
                    confirmDeleteDialog(j, cartItemView);
                }
            });

            CartItem productCartItem = (CartItem)application.getDataCart().getCartItems().get(i);

            ImageLoader imgLoader = new ImageLoader(getActivity());
            imgLoader.DisplayImage(productCartItem.getColorObject().getImage(), ivCartImg);

            tvName.setText(productCartItem.getProductDetail().getName());

            tvPrice.setText(String.valueOf(productCartItem.getCartItemPrice()));

            tvColor.setText(productCartItem.getColorObject().getColor_name());
            tvColor.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View paramAnonymousView)
                {
//                    new ChooseColor(new ChooseColor.ColorChooseCallback()
//                    {
//                        public void onColorChoice(ColorObject paramAnonymous2ColorObject)
//                        {
//                            this.val$item.colorObject = paramAnonymous2ColorObject;
//                            this.val$txtMauSanPham.setText(this.val$item.colorObject.getColorName());
//                            GioHangFragment.this.imageLoader.DisplayImage(this.val$item.colorObject.getImage(), this.val$imgSanPham);
//                        }
//                    }
//                            , localCartItem.product.getColorObjectArray()).show(GioHangFragment.this.getChildFragmentManager(), "choose_color_dialog");
                }
            });

            tvQuantity.setText(String.valueOf(productCartItem.getQuantity()));

            llCartList.addView(cartItemView);
        }
    }

    /**
     * Update value for Cart ListView
     */
    private void updateValue()
    {
        OneMarketApplication application = (OneMarketApplication)getActivity().getApplication();

        TextView tvNumber = (TextView)getView().findViewById(R.id.tv_cart_total_item);
        tvNumber.setText(getString(R.string.str_cart_total_item, application.getDataCart().getCartItems().size()));

        TextView tvPrice = (TextView)getView().findViewById(R.id.tv_cart_price);
        tvPrice.setText(Utils.getPriceFormat(""+application.getDataCart().getPrice()));

        TextView tvFee = (TextView)getView().findViewById(R.id.tv_cart_transport_fee);
        tvFee.setText(Utils.getPriceFormat(""+application.getDataCart().getTransportFee()));

        TextView tvTotal = (TextView)getView().findViewById(R.id.tv_cart_item_total);

        tvTotal.setText(Utils.getPriceFormat(""+application.getDataCart().getTotalPrice()));

        ScrollView svPayment = (ScrollView)getView().findViewById(R.id.sv_cart_payment);

        if (application.getDataCart().getCartItems().size() == 0){
            svPayment.setVisibility(View.GONE);
        }else {
            svPayment.setVisibility(View.VISIBLE);
        }
    }

    public void onFragmentUnselected()    {
        Log.i(TAG,"CartFragment - onFragmentUnselected");
        super.onFragmentUnselected();
    }

    private void confirmDeleteDialog(final int position, final View paramView)
    {
        final OneMarketApplication application = (OneMarketApplication)getActivity().getApplication();
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity());
        localBuilder.setMessage("Are you sure delete this product?");
        localBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
                application.removeCart(position);
//                llCartList.removeView(paramView);
                onFragmentSelected();
                ((MainActivity)getActivity()).mFootMenu.updateCartCount();
                updateValue();
                paramAnonymousDialogInterface.dismiss();
            }
        });
        localBuilder.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
                paramAnonymousDialogInterface.dismiss();
            }
        });
        localBuilder.show();
    }
}