package com.example.roundprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.example.circlepregress.R;

public class RoundProgressBar extends View {
	
	//画笔
	private Paint paint;
	//环形默认背景颜色
	private int roundColor;
	//环形进度背景颜色
	private int roundProgressColor;
	//进度显示字体颜色
	private int textColor;
	//进度显示字体大小
	private float textSize;
	//环形进度宽度
	private float roundWidth;
	//进度最大值，默认100
	private int max;
	//进度值
	private int progress;
	//进度文字显示的标识，默认为true
	private boolean textIsDisplayable;
	
	//进度样式，默认为环形样式
	private int style;
	//环形样式
	private static final int STROKE = 0;
	//饼状样式
	private static final int FILL = 1;
	
	private RectF oval;
	
	public RoundProgressBar(Context context) {
		this(context, null);
	}

	public RoundProgressBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public RoundProgressBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		paint = new Paint();
		
		TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);
		
		roundColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundColor, Color.RED);
		roundProgressColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundProgressColor, Color.GREEN);
		textColor = mTypedArray.getColor(R.styleable.RoundProgressBar_textColor, Color.GREEN);
		textSize = mTypedArray.getDimension(R.styleable.RoundProgressBar_textSize, 15);
		roundWidth = mTypedArray.getDimension(R.styleable.RoundProgressBar_roundWidth, 5);
		max = mTypedArray.getInteger(R.styleable.RoundProgressBar_max, 100);
		textIsDisplayable = mTypedArray.getBoolean(R.styleable.RoundProgressBar_textIsDisplayable, true);
		style = mTypedArray.getInt(R.styleable.RoundProgressBar_style, 0);
		
		mTypedArray.recycle();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		int centre = getWidth() / 2;
		int radius = (int) (centre - roundWidth / 2); 
		paint.setColor(roundColor); 
		paint.setStyle(Paint.Style.STROKE); 
		paint.setStrokeWidth(roundWidth); 
		paint.setAntiAlias(true);  
		canvas.drawCircle(centre, centre, radius, paint); 
		
		paint.setStrokeWidth(0); 
		paint.setColor(textColor);
		paint.setTextSize(textSize);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		
		int percent = progress * 100 / max; 
		float textWidth = paint.measureText(percent + "%");   
		
		if(textIsDisplayable && percent != 0 && style == STROKE){
			canvas.drawText(percent + "%", centre - textWidth / 2, centre + textSize / 2, paint); 
		}
		
		paint.setStrokeWidth(roundWidth); 
		paint.setColor(roundProgressColor);  
		
		if (oval == null) {
			oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius); 
		}
		
		switch (style) {
		case STROKE:
			paint.setStyle(Paint.Style.STROKE);
			canvas.drawArc(oval, 0, 360 * progress / max, false, paint);  
			break;
			
		case FILL:
			paint.setStyle(Paint.Style.FILL_AND_STROKE);
			if(progress !=0)
				canvas.drawArc(oval, 0, 360 * progress / max, true, paint);  
			break;
		}
		
	}
	
	
	public synchronized int getMax() {
		return max;
	}

	public synchronized void setMax(int max) {
		if(max < 0){
			throw new IllegalArgumentException("max not less than 0");
		}
		this.max = max;
	}

	public synchronized int getProgress() {
		return progress;
	}

	public synchronized void setProgress(int progress) {
		if(progress < 0){
			throw new IllegalArgumentException("progress not less than 0");
		}
		if(progress > max){
			progress = max;
		}
		if(progress <= max){
			this.progress = progress;
			postInvalidate();
		}
	}
	
	public int getCricleColor() {
		return roundColor;
	}

	public void setCricleColor(int cricleColor) {
		this.roundColor = cricleColor;
	}

	public int getCricleProgressColor() {
		return roundProgressColor;
	}

	public void setCricleProgressColor(int cricleProgressColor) {
		this.roundProgressColor = cricleProgressColor;
	}

	public int getTextColor() {
		return textColor;
	}

	public void setTextColor(int textColor) {
		this.textColor = textColor;
	}

	public float getTextSize() {
		return textSize;
	}

	public void setTextSize(float textSize) {
		this.textSize = textSize;
	}

	public float getRoundWidth() {
		return roundWidth;
	}

	public void setRoundWidth(float roundWidth) {
		this.roundWidth = roundWidth;
	}

}
