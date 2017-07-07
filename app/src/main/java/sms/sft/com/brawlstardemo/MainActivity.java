package sms.sft.com.brawlstardemo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import sms.sft.com.appbase.util.service.LocalSpiceService;
import sms.sft.com.brawlstardemo.dao.Helper;
import sms.sft.com.brawlstardemo.infoparser.ResponseLoadRequest;

public class MainActivity extends AppCompatActivity implements RequestListener<String> {

    @BindView(R.id.progress_easeofuse)
    RoundCornerProgressBar progressEasyOfUse;

    @BindView(R.id.progress_range)
    RoundCornerProgressBar progressRange;

    @BindView(R.id.progress_accuracy)
    RoundCornerProgressBar progressAccuracy;

    @BindView(R.id.progress_power)
    RoundCornerProgressBar progressPower;

    @BindView(R.id.progress_mobility)
    RoundCornerProgressBar progressMobility;

    @BindView(R.id.progress_stamina)
    RoundCornerProgressBar progressStamina;

    @BindView(R.id.progress_utility)
    RoundCornerProgressBar progressUtility;

    @BindView(R.id.progress_crowd_control)
    RoundCornerProgressBar progressCrowdControl;

    RoundCornerProgressBar[] listProgress;

    private SpiceManager mSpiceManager = new SpiceManager(LocalSpiceService.class);
    LocalUpdateService localUpdateService = BeanContainer.getInstance().getLocalUpdateService();


    @Override
    protected void onStart() {
        if (!mSpiceManager.isStarted()) {
            mSpiceManager.start(getApplicationContext());
            final int currentVersion = localUpdateService.getVersion(getApplicationContext());
            if (currentVersion != Helper.DATABASE_VERSION) {
               /* Handler handlerTimer = new Handler();
                handlerTimer.postDelayed(new Runnable() {
                    public void run() {
                        callUpdateResquest();
                    }
                }, 5000);*/
                runTask();
            } else {
                runTask();
            }
        }
        super.onStart();
    }


    private void callUpdateResquest() {
        mSpiceManager.execute(new UpdateLoadRequest(getApplicationContext()), this);
    }

    private LoadType mCurLoadType = LoadType.response;

    enum LoadType {
        response,
        cosmetic_items,
        cosmetic_items_english,
        cosmetic_items_russian
    }

    private void runTask() {
        switch (mCurLoadType) {
            case response:
                mSpiceManager.execute(new ResponseLoadRequest(getApplicationContext()), this);
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        listProgress = new RoundCornerProgressBar[8];

        listProgress[0] = progressEasyOfUse;
        listProgress[1] = progressRange;
        listProgress[2] = progressAccuracy;
        listProgress[3] = progressPower;
        listProgress[4] = progressMobility;
        listProgress[5] = progressStamina;
        listProgress[6] = progressUtility;
        listProgress[7] = progressCrowdControl;

        int numberProgress[] = {60, 60, 60, 60, 30, 33, 40, 99};
        for (int i = 0; i < 8; i++) {
            setProgressValue(i, numberProgress);
        }
    }

    private void setProgressValue(int index, int[] numberProgress) {
        listProgress[index].setProgressColor(Color.parseColor("#0884F8"));
        listProgress[index].setProgressBackgroundColor(Color.parseColor("#DA5B14"));
        listProgress[index].setMax(100);
        listProgress[index].setProgress(numberProgress[index]);
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {

    }


    @Override
    public void onRequestSuccess(String s) {
        localUpdateService.setUpdated(MainActivity.this);
    }
}
