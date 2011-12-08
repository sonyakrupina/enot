package app.tascact.manual;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class ManualView extends View
{
	// размеры экрана
	private int mWidth = 0;
	private int mHeight = 0;
	private Context mContext = null;
	
	private final Paint mPaint = new Paint();
	private CPage mPage = null;
	
	private int mPageRes[][] = {
			{R.drawable.pg5_1, R.drawable.pg5_2, R.drawable.pg5_3, R.drawable.pg5_4, R.drawable.pg5_footer},
			{R.drawable.pg12_1, R.drawable.pg12_2, R.drawable.pg12_footer}
			};
	
	private int mCurrPageNum = 0;
	private int mAllPagesNum = 0;
	
	
	private long mPrevTouchTime = 0;
    public ManualView(Context context)
    {
		super(context);
		
		mAllPagesNum = mPageRes.length;
		
		mContext = context;
		mPage = new CPage(mContext, mPageRes[0]);
	}
    
    // получение размеров экрана
    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mPage.setDims(w, h);
    }
    
    // отрисовка страницы
    @Override protected void onDraw(Canvas canvas) 
	{
		//canvas.drawBitmap(mBitmap, 0, 0, mPaint);
		mPage.drawPage(canvas, mPaint);
	}
    
    // обработка касаний
    @Override public boolean onTouchEvent(MotionEvent event)
	{
		// получаем action
	    int eventAction = event.getAction(); 
	    
	    // получаем координаты прикасания
	    float X = event.getX(); 
	    float Y = event.getY(); 
		
	    switch (eventAction)
	    { 
	    	// action - нажали на экран
		    case MotionEvent.ACTION_DOWN:
		    {
		    	
		    }
		    
		    // action - ведем палец по экрану
		    case MotionEvent.ACTION_MOVE:
		    {
		    }
		    
		    // action - убрали палец
		    case MotionEvent.ACTION_UP:
		    {
		    	if(event.getEventTime() - mPrevTouchTime > 250)
			    {
		    		mPrevTouchTime = event.getEventTime();
			    	int val = mPage.getTouchedImageNum((int)X, (int)Y);
			    	if(val == -1)
			    	{
			    		if(mCurrPageNum < mAllPagesNum - 1)
			    		{
			    			++mCurrPageNum;
			    		}
			    		else
			    		{
			    			mCurrPageNum = 0;
			    		}
			    		mPage = new CPage(mContext, mPageRes[mCurrPageNum]);
			    		mPage.setDims(mWidth, mHeight);
			    	}
		    	
			    	if(val == -2)
			    	{
			    		if(mCurrPageNum > 0)
			    		{
			    			--mCurrPageNum;
			    		}
			    		else
			    		{
			    			mCurrPageNum = mAllPagesNum - 1;
			    		}
			    		mPage = new CPage(mContext, mPageRes[mCurrPageNum]);
			    		mPage.setDims(mWidth, mHeight);
			    	}
		    	}
		    	invalidate();
		    }
	    }
		return true;
	}
}