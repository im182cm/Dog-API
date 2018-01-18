package philip.com.dogapi.mvp.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import philip.com.dogapi.R;
import philip.com.dogapi.mvp.main.MainActivity;

public class SplashActivity extends AppCompatActivity implements SplashContract.View {
    private SplashPresenter mSplashPresenter;
    private RequestManager mRequestManager;

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mSplashPresenter = new SplashPresenter(this);
        mRequestManager = Glide.with(this);

        mImageView = (ImageView) findViewById(R.id.splashIV);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRequestManager.asBitmap().load(R.drawable.dog_api_logo).into(mImageView);
        mSplashPresenter.start();
    }

    @Override
    public void goNextActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
