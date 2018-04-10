package com.blackchopper.arcmenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;


/**
 * author  : Black Chopper
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/BlackChopper
 * project : ArcMenu
 */
public class ArcMenu extends ViewGroup implements View.OnClickListener {

    private final static int LEFT_TOP = 0;
    private final static int LEFT_BOTTOM = 1;
    private final static int RIGHT_TOP = 2;
    private final static int RIGHT_BOTTOM = 3;


    private int mRadius;
    private Postion mPostion = Postion.RIGHT_BOTTOM;
    private Status mCurrentStatus = Status.CLOSE;
    private View mCenterButton;
    private OnArcMenuItemClickListener mLinstener;


    /**
     * 菜单的位置的枚举类型
     */
    public enum Postion {
        LEFT_TOP, LEFT_BOTTOM, RIGHT_TOP, RIGHT_BOTTOM
    }

    /**
     * 菜单的状态
     */
    public enum Status {
        OPEN, CLOSE
    }

    /**
     * 点击子菜单的回调接口
     */
    public interface OnArcMenuItemClickListener {
        void onClick(View item, int position);
    }

    public ArcMenu(Context context) {
        this(context, null);
    }

    public ArcMenu(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcMenu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
        //获取自定义属性
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ArcMenu);
        int pos = ta.getInt(R.styleable.ArcMenu_position, 3);
        switch (pos) {
            case LEFT_TOP:
                mPostion = Postion.LEFT_TOP;
                break;
            case LEFT_BOTTOM:
                mPostion = Postion.LEFT_BOTTOM;
                break;
            case RIGHT_TOP:
                mPostion = Postion.RIGHT_TOP;
                break;
            case RIGHT_BOTTOM:
                mPostion = Postion.RIGHT_BOTTOM;
                break;
        }
        mRadius = (int) ta.getDimension(R.styleable.ArcMenu_raduis, mRadius);
        ta.recycle();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int i0, int i1, int i2, int i3) {
        if (changed) {
            layoutCenterButton();
            int count = getChildCount();

            for (int i = 0; i < count - 1; i++) {
                View child = getChildAt(i + 1);
                child.setVisibility(GONE);
                int cl = (int) (mRadius * Math.sin(Math.PI / 2 / (count - 2) * i));
                int ct = (int) (mRadius * Math.cos(Math.PI / 2 / (count - 2) * i));
                int cWidth = child.getMeasuredWidth();
                int cHeight = child.getMeasuredHeight();

                // 如果菜单位置在底部 左下，右下
                if (mPostion == Postion.LEFT_BOTTOM || mPostion == Postion.RIGHT_BOTTOM) {
                    ct = getMeasuredHeight() - cHeight - ct;
                }
                // 右上，右下
                if (mPostion == Postion.RIGHT_TOP || mPostion == Postion.RIGHT_BOTTOM) {
                    cl = getMeasuredWidth() - cWidth - cl;
                }
                child.layout(cl, ct, cl + cWidth, ct + cHeight);
            }
        }

    }

    /**
     * 定位主菜单按钮
     */
    private void layoutCenterButton() {
        mCenterButton = getChildAt(0);
        mCenterButton.setOnClickListener(this);
        int l = 0;
        int t = 0;
        int w = mCenterButton.getMeasuredWidth();
        int h = mCenterButton.getMeasuredHeight();
        switch (mPostion) {
            case LEFT_TOP:
                l = 0;
                t = 0;
                break;
            case LEFT_BOTTOM:
                l = 0;
                t = getMeasuredHeight() - h;
                break;
            case RIGHT_TOP:
                l = getMeasuredWidth() - w;
                t = 0;
                break;
            case RIGHT_BOTTOM:
                l = getMeasuredWidth() - w;
                t = getMeasuredHeight() - h;
                break;
        }
        mCenterButton.layout(l, t, l + w, t + h);
    }


    public void setOnArcMenuItemClickListener(OnArcMenuItemClickListener mLinstener) {
        this.mLinstener = mLinstener;
    }

    @Override
    public void onClick(View view) {
        rotateCenterButtom(view, 0f, 360, 300);
        toggleMenu(300);
    }

    /**
     * 切换菜单
     */
    public void toggleMenu(int duration) {
        //为Item添加添加平移/旋转动画
        int count = getChildCount();
        for (int i = 0; i < count - 1; i++) {
            final View childView = getChildAt(i + 1);
            childView.setVisibility(VISIBLE);
            int cl = (int) (mRadius * Math.sin(Math.PI / 2 / (count - 2) * i));
            int ct = (int) (mRadius * Math.cos(Math.PI / 2 / (count - 2) * i));
            int xflag = 1;
            int yflag = 1;
            if (mPostion == Postion.LEFT_TOP || mPostion == Postion.LEFT_BOTTOM) {
                xflag = -1;
            }
            if (mPostion == Postion.LEFT_TOP || mPostion == Postion.RIGHT_TOP) {
                yflag = -1;
            }
            AnimationSet animSet = new AnimationSet(true);
            Animation tranAnim = null;
            //to open
            if (mCurrentStatus == Status.CLOSE) {
                tranAnim = new TranslateAnimation(xflag * cl, 0, yflag * ct, 0);
                childView.setClickable(true);
                childView.setFocusable(true);
            } else {
                tranAnim = new TranslateAnimation(0, xflag * cl, 0, yflag * ct);
                childView.setClickable(false);
                childView.setFocusable(false);
            }
            tranAnim.setFillAfter(true);
            tranAnim.setDuration(duration);
            tranAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (mCurrentStatus == Status.CLOSE) {
                        childView.setVisibility(GONE);
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            tranAnim.setStartOffset(i * 100 / count);
            //旋转动画
            RotateAnimation rotateAnim = new RotateAnimation(0, 720, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnim.setFillAfter(true);
            rotateAnim.setDuration(duration);
            animSet.addAnimation(rotateAnim);
            animSet.addAnimation(tranAnim);
            childView.startAnimation(animSet);
            final int pos = i + 1;
            childView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mLinstener != null) {
                        mLinstener.onClick(view, pos);
                    }
                    itemAnim(pos - 1);
                    changeStatus();
                }
            });

        }
        changeStatus();
    }

    public boolean isOpen() {
        return mCurrentStatus == Status.OPEN;
    }

    private void itemAnim(int pos) {
        for (int i = 0; i < getChildCount() - 1; i++) {
            View viewChild = getChildAt(i + 1);
            if (i == pos) {
                viewChild.startAnimation(scalsBigAnim(300));
            } else {
                viewChild.startAnimation(scalsSmallAnim(300));
            }
            viewChild.setClickable(false);
            viewChild.setFocusable(false);
        }
    }

    /**
     * 为当前点击的Item设置变小和透明度降低的动画
     */
    private Animation scalsBigAnim(int duration) {
        AnimationSet animSet = new AnimationSet(true);
        ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 4.0f, 1.0f, 4.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        AlphaAnimation alphaAnim = new AlphaAnimation(1, 0);
        animSet.addAnimation(scaleAnim);
        animSet.addAnimation(alphaAnim);
        animSet.setDuration(duration);
        animSet.setFillAfter(true);
        return animSet;
    }

    /**
     * 为当前点击的Item设置变大和透明度降低的动画
     */
    private Animation scalsSmallAnim(int duration) {
        AnimationSet animSet = new AnimationSet(true);
        ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        AlphaAnimation alphaAnim = new AlphaAnimation(1, 0);
        animSet.addAnimation(scaleAnim);
        animSet.addAnimation(alphaAnim);
        animSet.setDuration(duration);
        animSet.setFillAfter(true);
        return animSet;
    }

    private void changeStatus() {
        mCurrentStatus = (mCurrentStatus == Status.CLOSE ? Status.OPEN : Status.CLOSE);
    }

    private void rotateCenterButtom(View view, float start, int end, int duration) {
        RotateAnimation anim = new RotateAnimation(start, end, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(duration);
        anim.setFillAfter(true);
        view.startAnimation(anim);
    }
}
