package com.onesys.onemarket.utils.quickaction;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.onesys.onemarket.R;
import com.onesys.onemarket.adapter.DialogAdapter;

import java.util.ArrayList;

public class SearchByQuickAction extends PopupWindows {
	private ImageView mArrowUp;
	private ImageView mArrowDown;
	private Animation mTrackAnim;
	private LayoutInflater inflater;
	private ViewGroup mTrack;
	private OnActionItemClickListener mListener;

	private int animStyle;
	private int mChildPos;
	private boolean animateTrack;

	public static final int ANIM_GROW_FROM_LEFT = 1;
	public static final int ANIM_GROW_FROM_RIGHT = 2;
	public static final int ANIM_GROW_FROM_CENTER = 3;
	public static final int ANIM_AUTO = 4;

    private AlertDialog alert;
    private ArrayList<SearchByActionItem> searchByItems = new ArrayList();

	public SearchByQuickAction(Context context) {
		super(context);

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		mTrackAnim = AnimationUtils.loadAnimation(context, R.anim.rail);

		mTrackAnim.setInterpolator(new Interpolator() {
			public float getInterpolation(float t) {
				final float inner = (t * 1.55f) - 1.1f;

				return 1.2f - inner * inner;
			}
		});

		setRootViewId(R.layout.layout_quickaction_searchby);

		animStyle = ANIM_AUTO;
		animateTrack = true;
		mChildPos = 0;
	}

	public void setRootViewId(int id) {
		mRootView = (ViewGroup) inflater.inflate(id, null);
		mTrack = (ViewGroup) mRootView.findViewById(R.id.tracks);

		mArrowDown = (ImageView) mRootView.findViewById(R.id.arrow_down);
		mArrowUp = (ImageView) mRootView.findViewById(R.id.arrow_up);

        TextView mCancel = (TextView)mRootView.findViewById(R.id.tv_searchby_item_cancel);
        mCancel.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                dismiss();
            }
        });

        TextView mSelect = (TextView)mRootView.findViewById(R.id.tv_searchby_item_select);
        mSelect.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                if (mListener != null) {
                    //get selectedOption
                    String selectedPrice = "";
                    String selectedBrand = "";
                    String selectedOS = "";
                    String selectedScreen = "";
                    String selectedFeature = "";
                    String selectedPromotion = "";

                    for(SearchByActionItem item : searchByItems) {
                        if (item.getId() == 0) {
                            selectedPrice = item.getCurrentValue().id;
                        } else if (item.getId() == 1) {
                            selectedBrand = item.getCurrentValue().id;
                        } else if (item.getId() == 2){
                            selectedOS = item.getCurrentValue().id;
                        }else if (item.getId() == 3) {
                            selectedScreen = item.getCurrentValue().id;
                        }else if (item.getId() == 4) {
                            selectedFeature = item.getCurrentValue().id;
                        }else if (item.getId() == 5) {
                            selectedPromotion = item.getCurrentValue().id;
                        }
                    }

                  mListener.onItemAcceptClick(selectedPrice, selectedBrand, selectedOS, selectedScreen, selectedFeature, selectedPromotion);
                }
                dismiss();
            }
        });

		setContentView(mRootView);
	}

	public void animateTrack(boolean animateTrack) {
		this.animateTrack = animateTrack;
	}

	public void setAnimStyle(int animStyle) {
		this.animStyle = animStyle;
	}

	public void addActionItem(final SearchByActionItem action) {

        searchByItems.add(action);

		String actionTitle = action.getTitle();
        String actionValue = "";

		View container = (View) inflater.inflate(R.layout.quickaction_searchby_item, null);

		TextView title = (TextView) container.findViewById(R.id.tv_searchby_item_title);

		if (title != null)
            title.setText(actionTitle);
		else
            title.setVisibility(View.GONE);

        final TextView value = (TextView) container.findViewById(R.id.tv_searchby_item_value);

        if ((actionValue == null) || (actionValue.equals("Cancel")) || (actionValue.equals("Filter"))){
            value.setVisibility(View.GONE);
        }else{
            value.setVisibility(View.VISIBLE);
            value.setText(actionValue);
        }

		final int pos = mChildPos;

        final CheckBox chkSelect = (CheckBox)container.findViewById(R.id.cb_searchby_select);
        chkSelect.setChecked(false);
        chkSelect.setClickable(false);
        chkSelect.setVisibility(View.INVISIBLE);

        container.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                showDialog(action, value, chkSelect);

            }
        });

		container.setFocusable(true);
		container.setClickable(true);

		mTrack.addView(container, mChildPos + 1);

		mChildPos++;
	}

	public void setOnActionItemClickListener(OnActionItemClickListener listener) {
		mListener = listener;
	}

	public void show(View anchor) {
		preShow();

		int[] location = new int[2];

		anchor.getLocationOnScreen(location);

		Rect anchorRect = new Rect(location[0], location[1], location[0]
				+ anchor.getWidth(), location[1] + anchor.getHeight());

		mRootView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		mRootView.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		int rootWidth = mRootView.getMeasuredWidth();
		int rootHeight = mRootView.getMeasuredHeight();

		int screenWidth = mWindowManager.getDefaultDisplay().getWidth();

		int xPos = (screenWidth - rootWidth) / 2;
		int yPos = anchorRect.top - rootHeight;

		boolean onTop = true;

		if (rootHeight > anchor.getTop()) {
			yPos = anchorRect.bottom;
			onTop = false;
		}

		showArrow(((onTop) ? R.id.arrow_down : R.id.arrow_up),
				anchorRect.centerX());

		setAnimationStyle(screenWidth, anchorRect.centerX(), onTop);

		mWindow.showAtLocation(anchor, Gravity.NO_GRAVITY, xPos, yPos);

		if (animateTrack)
			mTrack.startAnimation(mTrackAnim);
	}

	private void setAnimationStyle(int screenWidth, int requestedX,
			boolean onTop) {
		int arrowPos = requestedX - mArrowUp.getMeasuredWidth() / 2;

		switch (animStyle) {
		case ANIM_GROW_FROM_LEFT:
			mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Left
					: R.style.Animations_PopDownMenu_Left);
			break;

		case ANIM_GROW_FROM_RIGHT:
			mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Right
					: R.style.Animations_PopDownMenu_Right);
			break;

		case ANIM_GROW_FROM_CENTER:
			mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Center
					: R.style.Animations_PopDownMenu_Center);
			break;

		case ANIM_AUTO:
			if (arrowPos <= screenWidth / 4) {
				mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Left
						: R.style.Animations_PopDownMenu_Left);
			} else if (arrowPos > screenWidth / 4
					&& arrowPos < 3 * (screenWidth / 4)) {
				mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Center
						: R.style.Animations_PopDownMenu_Center);
			} else {
				mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopDownMenu_Right
						: R.style.Animations_PopDownMenu_Right);
			}

			break;
		}
	}

	private void showArrow(int whichArrow, int requestedX) {
		final View showArrow = (whichArrow == R.id.arrow_up) ? mArrowUp
				: mArrowDown;
		final View hideArrow = (whichArrow == R.id.arrow_up) ? mArrowDown
				: mArrowUp;

		final int arrowWidth = mArrowUp.getMeasuredWidth();

		showArrow.setVisibility(View.VISIBLE);

		ViewGroup.MarginLayoutParams param = (ViewGroup.MarginLayoutParams) showArrow
				.getLayoutParams();

		param.leftMargin = requestedX - arrowWidth / 2;

		hideArrow.setVisibility(View.INVISIBLE);
	}

	public interface OnActionItemClickListener {
		public abstract void onItemAcceptClick(String price,String brand, String paramString3, String paramString4, String paramString5, String status);
	}


    private void showDialog(final SearchByActionItem paramActionTextItem, final TextView paramTextView, final CheckBox paramCheckBox)
    {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.mContext);
        View localView = ((LayoutInflater)this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.dialog_quickaction_searchby, null, false);
        localBuilder.setView(localView);
        ListView localListView = (ListView)localView.findViewById(R.id.dialog_quickaction_listview);
        localListView.setAdapter(new DialogAdapter(this.mContext, 17367043, paramActionTextItem.getArrayValues()));
        localListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
            {
                paramActionTextItem.setCurrentValuePosition(paramAnonymousInt);
                if ((paramActionTextItem.getCurrentValue() != null) && (!paramActionTextItem.getCurrentValue().name.equals("Cancel")) && (!paramActionTextItem.getCurrentValue().name.equals("Filter")))
                {
                    paramTextView.setVisibility(View.VISIBLE);
                    paramTextView.setText(paramActionTextItem.getCurrentValue().name);
                    paramCheckBox.setVisibility(View.GONE);
                }else if ((paramActionTextItem.getCurrentValue() != null) && (paramActionTextItem.getCurrentValue().name.equals("Filter"))){
                    paramTextView.setVisibility(View.GONE);
                    paramCheckBox.setChecked(true);
                    paramCheckBox.setVisibility(View.VISIBLE);
                }else{
                    paramTextView.setVisibility(View.GONE);
                    paramCheckBox.setVisibility(View.GONE);
                }

                alert.dismiss();
            }
        });
        this.alert = localBuilder.create();
        this.alert.setTitle(paramActionTextItem.getTitle());
        this.alert.show();
    }

}