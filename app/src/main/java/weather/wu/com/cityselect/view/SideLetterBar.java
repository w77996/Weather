package weather.wu.com.cityselect.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import weather.wu.com.weather.R;

/**
 * Created by 吴海辉 on 2017/1/16.
 */
public class SideLetterBar extends View {
    private static final String[] letter = {"定位", "热门", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private int choose = -1;
    private Paint mPaint = new Paint();
    private boolean showBg = false;
    private OnLetterChangedListener mOnLetterChangedListener;
    private TextView overlay;//出现字母框

    public SideLetterBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SideLetterBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SideLetterBar(Context context) {
        super(context);
    }

    /**
     * 设置悬浮的textview
     * @param overlay
     */
    public void setOverlay(TextView overlay){
        this.overlay = overlay;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (showBg) {
            canvas.drawColor(Color.TRANSPARENT);
        }

        int height = getHeight();
        int width = getWidth();
        int singleHeight = height / letter.length;//单个字母的高度
        for (int i = 0; i < letter.length; i++) {
            mPaint.setTextSize(getResources().getDimension(R.dimen.side_letter_bar_letter_size));
            mPaint.setColor(getResources().getColor(R.color.cityselect_gray));
            mPaint.setAntiAlias(true);//锯齿
            if (i == choose) {
                mPaint.setColor(getResources().getColor(R.color.cityselect_gray_deep));
//                mPaint.setFakeBoldText(true);  //加粗
            }
            float xPos = width / 2 - mPaint.measureText(letter[i]) / 2;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(letter[i], xPos, yPos, mPaint);
            mPaint.reset();
        }

    }

    /**
     * 事件拦截
     * @param event
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();//判断事件的点击
        final int oldChoose = choose;
        final OnLetterChangedListener listener = mOnLetterChangedListener;
        final int c = (int) (y / getHeight() * letter.length);//回去字母的在View中的位子

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                showBg = true;
                if (oldChoose != c && listener != null) {
                    //点击的事件获取到的字母的位子
                    if (c >= 0 && c < letter.length) {
                        listener.onLetterChanged(letter[c]);
                        choose = c;
                        invalidate();//更新
                        if (overlay != null){
                            overlay.setVisibility(VISIBLE);
                            overlay.setText(letter[c]);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (oldChoose != c && listener != null) {
                    if (c >= 0 && c < letter.length) {
                        listener.onLetterChanged(letter[c]);
                        choose = c;
                        invalidate();
                        if (overlay != null){
                            overlay.setVisibility(VISIBLE);
                            overlay.setText(letter[c]);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                showBg = false;
                choose = -1;
                invalidate();
                if (overlay != null){
                    overlay.setVisibility(GONE);
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
    //外部接口通过回调回去listener
    public void setmOnLetterChangedListener(OnLetterChangedListener mOnLetterChangedListener) {
        this.mOnLetterChangedListener = mOnLetterChangedListener;
    }

    public interface OnLetterChangedListener {
        void onLetterChanged(String letter);
    }

}
