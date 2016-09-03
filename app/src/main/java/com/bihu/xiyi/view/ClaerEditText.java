package com.bihu.xiyi.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.bihu.xiyi.R;

import org.w3c.dom.Attr;

import java.lang.annotation.Annotation;

import butterknife.OnTextChanged;

/**
 * Created by luo on 2016/8/24.
 */
public class ClaerEditText extends EditText implements TextWatcher,View.OnFocusChangeListener {
    private Drawable mdrawable;
    public ClaerEditText(Context context, AttributeSet attrs) {
        this(context,attrs,android.R.attr.editTextStyle);
    }
    // android.R.attr.editTextStyle
    public ClaerEditText(Context context) {
        this(context,null);
    }

    public ClaerEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public void init(){
         mdrawable = getCompoundDrawables()[2];
        if(mdrawable==null){
            mdrawable=getResources().getDrawable(R.drawable.eer_text_empty);
        }
        addTextChangedListener(this);
        setClearText(false);
        setOnFocusChangeListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            int x= (int) event.getX();
            int y= (int) event.getY();
            int a=(getHeight()-mdrawable.getIntrinsicHeight())/2;
            Boolean bx=x>getWidth()-getTotalPaddingRight()&&x<getWidth()-getPaddingRight();
            Boolean by=y>a&&y<a+mdrawable.getIntrinsicHeight();
            if(bx&&by) {
                setText("");
            }
        }
        return super.onTouchEvent(event);
    }

    public void setClearText(Boolean flase){
        Drawable leftDrawable = getCompoundDrawables()[0];
        Drawable toptDrawable = getCompoundDrawables()[1];
        Drawable rightDrawable=mdrawable;
        if(!flase)
            rightDrawable=null;
        Drawable bottonDrawable = getCompoundDrawables()[3];
        setCompoundDrawablesWithIntrinsicBounds(leftDrawable,toptDrawable,rightDrawable,bottonDrawable);
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }

    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        //super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if(text.length()==0){
            setClearText(false);
        }else {
            setClearText(true);
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if(!b){
            setClearText(b);
        }else {
            setClearText(b);
        }
    }
}
