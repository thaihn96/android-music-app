package com.framgia.thaihn.tmusic.screen.splash;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.framgia.thaihn.tmusic.BaseActivity;
import com.framgia.thaihn.tmusic.R;
import com.framgia.thaihn.tmusic.screen.main.MainActivity;

public class SplashActivity extends BaseActivity implements SplashContract.View {

    private SplashContract.Presenter mPresenter;
    private TextView mTextSplash;

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        mTextSplash = findViewById(R.id.text_splash);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mTextSplash.setText(R.string.str_text_splash);
        Typeface type = Typeface.createFromAsset(getAssets(), "font.TTF");
        mTextSplash.setTypeface(type);
        mPresenter = new SplashPresenter();
        mPresenter.setView(this);
        mPresenter.waitScreen();
    }

    @Override
    public void waitSuccess() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void waitFail() {
    }

    @Override
    public void changeColorStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.color_chocolate));
        }
    }
}
