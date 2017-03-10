package com.application.davidelm.housefly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.application.davidelm.housefly.application.HouseflyApplication;
import com.application.davidelm.housefly.views.RepoOwnerDataView;
import com.application.subitoit.githubstargazers.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RepoOwnerDataView repoOwnerDataView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindView();
        onInitView();
    }

    private void bindView() {
        repoOwnerDataView = (RepoOwnerDataView) findViewById(R.id.repoOwnerDataViewId);
    }

    /**
     * init view to handle button in custom view interaction
     */
    private void onInitView() {
        repoOwnerDataView.setFindButtonOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!repoOwnerDataView.isValidInputData()) {
            repoOwnerDataView.setErrorInputData();
            return;
        }

        HouseflyApplication application = ((HouseflyApplication) getApplication());
        application.setOwner(repoOwnerDataView.getOwner());
        application.setRepo(repoOwnerDataView.getRepo());
        startActivity(new Intent(this, HouseListActivity.class));
    }
}
