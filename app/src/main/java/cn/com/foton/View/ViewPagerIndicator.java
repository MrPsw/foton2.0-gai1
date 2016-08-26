package cn.com.foton.View;


import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.com.foton.R;



/**
 * 
 * @author hongyangAndroid
 http://blog.csdn.net/lmj623565791/
 * 
 */
public class ViewPagerIndicator extends LinearLayout
{

	private Paint mPaint;

	private Path mPath;

	private int mTriangleWidth;

	private int mTriangleHeight;

	private static final float RADIO_TRIANGLE_WIDTH = 1 / 6F;
	/**
	 * 娑撳顫楄ぐ銏犵俺鏉堝湱娈戦張锟姐亣鐎硅棄瀹�	 */
	private final int DIMENSION_TRIANGLE_WIDTH_MAX = (int) (getScreenWidth() / 3 * RADIO_TRIANGLE_WIDTH);

	private int mInitTranslationX;

	private int mTranslationX;

	private int mTabVisibleCount;

	private static final int COUNT_DEFAULT_TAB = 4;
	private static final int COLOR_TEXT_NORMAL = 0x77FFFFFF;
	private static final int COLOR_TEXT_HIGHLIGHT = 0xFFFFFFFF;

	private List<String> mTitles;

	public ViewPagerIndicator(Context context)
	{
		this(context, null);
	}

	public ViewPagerIndicator(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		// 閼惧嘲褰囬崣顖濐潌Tab閻ㄥ嫭鏆熼柌锟�	
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.ViewPagerIndicator);

		mTabVisibleCount = a.getInt(
				R.styleable.ViewPagerIndicator_visible_tab_count,
				COUNT_DEFAULT_TAB);
		if (mTabVisibleCount < 0)
		{
			mTabVisibleCount = COUNT_DEFAULT_TAB;
		}
		a.recycle();

		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.parseColor("#ffffffff"));
		mPaint.setStyle(Style.FILL);
		mPaint.setPathEffect(new CornerPathEffect(3));

	}

	@Override
	protected void dispatchDraw(Canvas canvas)
	{

		canvas.save();

		canvas.translate(mInitTranslationX + mTranslationX, getHeight() + 2);
		canvas.drawPath(mPath, mPaint);

		canvas.restore();

		super.dispatchDraw(canvas);

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w, h, oldw, oldh);

		mTriangleWidth = (int) (w / mTabVisibleCount * RADIO_TRIANGLE_WIDTH);
		mTriangleWidth = Math.min(mTriangleWidth, DIMENSION_TRIANGLE_WIDTH_MAX);
		mInitTranslationX = w / mTabVisibleCount / 2 - mTriangleWidth / 2;

		initTriangle();

	}

	@Override
	protected void onFinishInflate()
	{
		super.onFinishInflate();

		int cCount = getChildCount();
		if (cCount == 0)
			return;

		for (int i = 0; i < cCount; i++)
		{
			View view = getChildAt(i);
			LinearLayout.LayoutParams lp = (LayoutParams) view
					.getLayoutParams();
			lp.weight = 0;
			lp.width = getScreenWidth() / mTabVisibleCount;
			view.setLayoutParams(lp);
		}

		setItemClickEvent();

	}

	/**
	 * 閼惧嘲绶辩仦蹇撶閻ㄥ嫬顔旀惔锟� * 
	 * @return
	 */
	private int getScreenWidth()
	{
		WindowManager wm = (WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}

	/**
	 * 閸掓繂顫愰崠鏍︾瑏鐟欐帒鑸�	 */
	private void initTriangle()
	{

		mTriangleHeight = mTriangleWidth / 2;

		mPath = new Path();
		mPath.moveTo(0, 0);
		mPath.lineTo(mTriangleWidth, 0);
		mPath.lineTo(mTriangleWidth / 2, -mTriangleHeight);
		mPath.close();

	}

	/**
	 * 閹稿洨銇氶崳銊ㄧ闂呭繑澧滈幐鍥箻鐞涘本绮撮崝锟� * 
	 * @param position
	 * @param offset
	 */
	public void scroll(int position, float offset)
	{
		int tabWidth = getWidth() / mTabVisibleCount;
		mTranslationX = (int) (tabWidth * (offset + position));

		// 鐎圭懓娅掔粔璇插З閿涘苯婀猼ab婢跺嫪绨粔璇插З閼疯櫕娓堕崥搴濈娑擃亝妞�		
		if (position >= (mTabVisibleCount - 2) && offset > 0
				&& getChildCount() > mTabVisibleCount)
		{

			if (mTabVisibleCount != 1)
			{
//				Log.e("ViewPagerIndicator",
//						((position - (mTabVisibleCount - 2)) * tabWidth + (int) (tabWidth * offset))
//								+ "");
				this.scrollTo((position - (mTabVisibleCount - 2)) * tabWidth
						+ (int) (tabWidth * offset), 0);
			} else
			{
				this.scrollTo(position * tabWidth + (int) (tabWidth * offset),
						0);
			}

		}

		invalidate();

	}

	public void setTabItemTitles(List<String> titles)
	{
		if (titles != null && titles.size() > 0)
		{
			this.removeAllViews();
			mTitles = titles;
			for (String title : mTitles)
			{
				addView(generateTextView(title));
			}

			setItemClickEvent();
		}
	}

	/**
	 * 鐠佸墽鐤嗛崣顖濐潌閻ㄥ嚲ab閺佷即鍣�	 * 
	 * @param count
	 */
	public void setVisibleTabCount(int count)
	{
		mTabVisibleCount = count;
	}

	/**
	 * 閺嶈宓乼itle閸掓稑缂揟ab
	 * 
	 * @param title
	 * @return
	 */
	private View generateTextView(String title)
	{
		TextView tv = new TextView(getContext());
		LinearLayout.LayoutParams lp = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		lp.width = getScreenWidth() / mTabVisibleCount;
		tv.setText(title);
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
		tv.setTextColor(COLOR_TEXT_NORMAL);
		tv.setLayoutParams(lp);
		return tv;
	}

	private ViewPager mViewPager;

	public interface PageOnchangeListener
	{
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels);

		public void onPageSelected(int position);

		public void onPageScrollStateChanged(int state);
	}

	public PageOnchangeListener mListener;

	public void setOnPageChangeListener(PageOnchangeListener listener)
	{
		this.mListener = listener;
	}

	/**
	 * 鐠佸墽鐤嗛崗瀹犱粓閻ㄥ垍iewPager
	 * 
	 * @param viewPager
	 * @param pos
	 */
	public void setViewPager(ViewPager viewPager, int pos)
	{
		mViewPager = viewPager;
		mViewPager.setOnPageChangeListener(new OnPageChangeListener()
		{

			@Override
			public void onPageSelected(int position)
			{

				if (mListener != null)
				{
					mListener.onPageSelected(position);
				}

				highLightTextView(position);
				// 閺嬩胶顏幆鍛枌閻ㄥ嚉ug娣囶喖顦�				if (position <= (mTabVisibleCount - 2))
					scrollTo(0, 0);

			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels)
			{
				scroll(position, positionOffset);
				if (mListener != null)
				{
					mListener.onPageScrolled(position, positionOffset,
							positionOffsetPixels);
				}
			}

			@Override
			public void onPageScrollStateChanged(int state)
			{

				if (mListener != null)
				{
					mListener.onPageScrollStateChanged(state);
				}

			}
		});
		mViewPager.setCurrentItem(pos);
		highLightTextView(pos);
	}

	/**
	 * 闁插秶鐤員AB閺傚洦婀版０婊嗗
	 */
	private void resetTextViewColor()
	{
		for (int i = 0; i < getChildCount(); i++)
		{
			View view = getChildAt(i);
			if (view instanceof TextView)
			{
				((TextView) view).setTextColor(COLOR_TEXT_NORMAL);
			}
		}

	}

	/**
	 * 妤傛ü瀵掗弻鎰嚋Tab閻ㄥ嫭鏋冮張锟� * 
	 * @param pos
	 */
	private void highLightTextView(int pos)
	{
		resetTextViewColor();
		View view = getChildAt(pos);
		if (view instanceof TextView)
		{
			((TextView) view).setTextColor(COLOR_TEXT_HIGHLIGHT);
		}
	}

	/**
	 * 鐠佸墽鐤員ab閻ㄥ嫮鍋ｉ崙璁崇皑娴狅拷	 */
	private void setItemClickEvent()
	{
		int cCount = getChildCount();

		for (int i = 0; i < cCount; i++)
		{
			final int j = i;
			View view = getChildAt(i);

			view.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					mViewPager.setCurrentItem(j);
				}
			});

		}

	}

}
