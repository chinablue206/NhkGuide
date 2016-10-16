package jp.rsks.nhkguide;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v17.leanback.app.DetailsFragment;
import android.support.v17.leanback.widget.Action;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.ClassPresenterSelector;
import android.support.v17.leanback.widget.DetailsOverviewRow;
import android.support.v17.leanback.widget.FullWidthDetailsOverviewRowPresenter;
import android.support.v17.leanback.widget.SparseArrayObjectAdapter;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;


import jp.rsks.nhkguide.datasource.NhkProgramList;

/**
 * Created by rsk on 2016/09/22.
 */

public class ProgramDetailsFragment extends DetailsFragment {
    private static final String TAG = "ProgamDtailsFragment";
    private static final int DETAIL_THUMB_WIDTH = 274;
    private static final int DETAIL_THUMB_HEIGHT = 274;

    private NhkProgramList.NhkProgram selectedProgram;

    private FullWidthDetailsOverviewRowPresenter fwdoPresenter;
    private ClassPresenterSelector classPresenterSelector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        selectedProgram =
                (NhkProgramList.NhkProgram) getActivity()
                .getIntent()
                .getSerializableExtra(DetailsActivity.PROGRAM);

        if(selectedProgram != null){
            fwdoPresenter = new FullWidthDetailsOverviewRowPresenter(new DetailsDescriptionPresenter());
            setupDetailOverviewRow(selectedProgram);
        } else {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void setupDetailOverviewRow(NhkProgramList.NhkProgram selectedProgram){
        final DetailsOverviewRow row = new DetailsOverviewRow(selectedProgram);

        int width = Utils.convertDpToPixel(getActivity()
                .getApplicationContext(), DETAIL_THUMB_WIDTH);
        int height = Utils.convertDpToPixel(getActivity()
                .getApplicationContext(), DETAIL_THUMB_HEIGHT);

        Glide.with(getActivity())
                .load("http:" + selectedProgram.service.logo_l.url)
                .asBitmap()
                .error(R.drawable.default_background)
                .into(new SimpleTarget<Bitmap>(width, height) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        row.setImageBitmap(getActivity(), resource);
                        startEntranceTransition();
                    }
                });

        SparseArrayObjectAdapter sparseArrayObjectAdapter = new SparseArrayObjectAdapter();
        for(int i = 0; i < 10; i++){
            sparseArrayObjectAdapter.set(i, new Action(i, "label1", "label2"));
        }

        row.setActionsAdapter(sparseArrayObjectAdapter);

        classPresenterSelector = new ClassPresenterSelector();
        fwdoPresenter.setInitialState(FullWidthDetailsOverviewRowPresenter.STATE_SMALL);

        classPresenterSelector.addClassPresenter(DetailsOverviewRow.class, fwdoPresenter);

        ArrayObjectAdapter adapter = new ArrayObjectAdapter(classPresenterSelector);
        adapter.add(row);
        setAdapter(adapter);
    }

}
