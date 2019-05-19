package com.example.milinear;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;


public class LinearLayoutCustom extends LinearLayout {
    private boolean usado;

    public LinearLayoutCustom(Context context) {
        super(context);
        this.usado = false;
    }

    public LinearLayoutCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.usado = false;
    }

    public LinearLayoutCustom(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.usado = false;
    }
    public boolean isUsado() {
        return this.usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }
}
