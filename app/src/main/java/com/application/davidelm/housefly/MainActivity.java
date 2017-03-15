package com.application.davidelm.housefly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.application.davidelm.housefly.application.HouseflyApplication;
import com.application.davidelm.housefly.managers.HtmlPageParser;
import com.application.davidelm.housefly.views.RepoOwnerDataView;
import com.application.subitoit.githubstargazers.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.repoOwnerDataViewId)
    RepoOwnerDataView repoOwnerDataView;
    private Unbinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binder = ButterKnife.bind(this);

        onInitView();
    }

    @Override
    protected void onDestroy() {
        if (binder != null)
            binder.unbind();
        super.onDestroy();
    }

    /**
     * init view to handle button in custom view interaction
     */
    private void onInitView() {
        repoOwnerDataView.setFindButtonOnClickListener(this);
        new HtmlPageParser().parseHtml();
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
