package com.onesys.onemarket;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.onesys.onemarket.slidemenu.NavDrawerItem;
import com.onesys.onemarket.slidemenu.NavDrawerListAdapter;
import com.onesys.onemarket.utils.BaseFragment;
import com.onesys.onemarket.utils.FooterFragment;
import com.onesys.onemarket.view.AccessoriesFragment;
import com.onesys.onemarket.view.CartFragment;
import com.onesys.onemarket.view.GameFragment;
import com.onesys.onemarket.view.HomeFragment;
import com.onesys.onemarket.view.LaptopFragment;
import com.onesys.onemarket.view.PhoneFragment;
import com.onesys.onemarket.view.SearchFragment;
import com.onesys.onemarket.view.StoreFragment;
import com.onesys.onemarket.view.TabletFragment;

public class MainActivity extends FragmentActivity
        implements BaseFragment.OnFragmentAttachedListener,
        BaseFragment.ShowContentListener{

    private static final String TAG = "OneMarket";

    public static final int HOME_VIEW = 6;
    public static final int SEARCH_VIEW = 7;
    public static final int CART_VIEW = 8;
    public static final int PROMOTION_VIEW = 9;
    public static final int MORE_VIEW = 10;
    public static final int PRODUCT_DETAIL_VIEW = 10;

    public static final int STORE_VIEW = 0;
    public static final int PHONE_VIEW = 1;
    public static final int LAPTOP_VIEW = 2;
    public static final int TABLET_VIEW = 3;
    public static final int GAME_VIEW = 4;
    public static final int ACCESSORIES_VIEW = 5;

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;

    public FooterFragment mFootMenu;
    private boolean isVisbleFooter = true;

    public ArrayList<Fragment> mListScreen = new ArrayList();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTitle = mDrawerTitle = getTitle();

        initNaviDrawer();
        setFooter();

		if (savedInstanceState == null) {
			// on first time display view for first nav item
            showContent(HOME_VIEW);
		}
	}

    private void initNaviDrawer(){
        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Find People
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Photos
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // Communities, Will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));
        // Pages
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        // What's hot, We  will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1), true, "50+"));


        // Recycle the typed array
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);

        // enabling action bar app icon and behaving it as toggle button
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
            showContent(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	public void showContent(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
        FragmentManager fragmentManager = getFragmentManager();

        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		switch (position) {
            case HOME_VIEW:
                fragment = new HomeFragment();
                fragmentTransaction.add(R.id.frame_container, (Fragment)fragment, "" + HOME_VIEW);
                break;
            case SEARCH_VIEW:
                fragment = new SearchFragment();
                break;
            case CART_VIEW:
                fragment = new CartFragment();
                break;
            case STORE_VIEW:
                fragment = new StoreFragment();
                fragmentTransaction.add(R.id.frame_container, (Fragment)fragment, "" + STORE_VIEW);
                break;
            case PHONE_VIEW:
                fragment = new PhoneFragment();
                fragmentTransaction.add(R.id.frame_container, (Fragment)fragment, "" + PHONE_VIEW);
                break;
            case LAPTOP_VIEW:
                fragment = new LaptopFragment();
                break;
            case TABLET_VIEW:
                fragment = new TabletFragment();
                break;
            case GAME_VIEW:
                fragment = new GameFragment();
                break;
            case ACCESSORIES_VIEW:
                fragment = new AccessoriesFragment();
                break;

            default:
                break;
        }

        if (fragment != null) {
            if (!this.mListScreen.contains(fragment)) {
                this.mListScreen.add(fragment);
            }

            hideAllFragment(fragmentTransaction);

//            fragmentManager.beginTransaction()
//                    .replace(R.id.frame_container, fragment).commit();
            fragmentTransaction.show(fragment);
            fragmentTransaction.commitAllowingStateLoss();

            // update selected item and title, then close the drawer
            if(position == HOME_VIEW){
                setTitle(R.string.str_footer_home);
            }else if(position == SEARCH_VIEW){
                setTitle(R.string.str_footer_search);
            }else if(position == CART_VIEW){
                setTitle(R.string.str_footer_cart);
            }else if(position == PRODUCT_DETAIL_VIEW){
                setTitle(R.string.str_product_detail);
            }else { // drawer views
                mDrawerList.setItemChecked(position, true);
                mDrawerList.setSelection(position);
                setTitle(navMenuTitles[position]);
                mDrawerLayout.closeDrawer(mDrawerList);
            }

        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

    public void OnFragmentAttached()
    {
    }

    private void setFooter()
    {
        FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (this.mFootMenu == null)
            this.mFootMenu = new FooterFragment();
        localFragmentTransaction.replace(R.id.footer_frame, this.mFootMenu);
        localFragmentTransaction.commitAllowingStateLoss();
        this.isVisbleFooter = true;
    }

    public void hideAllFragment(android.app.FragmentTransaction fragmentTransaction){
        for(Fragment fragment : mListScreen){
            Log.i(TAG, "Hide fragment : " + fragment.getTag());
            fragmentTransaction.hide(fragment);
        }
    }

}
