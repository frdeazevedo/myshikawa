package br.fazevedo.myshikawa;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import br.fazevedo.myshikawa.db.entity.Shikawa;
import br.fazevedo.myshikawa.screen.ShikawasListFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String ARG_CURR_SCREEN = "arg-curr-screen";

    @BindView(R.id.fab)
    FloatingActionButton mFAB;

    private enum Screen {
        SHIKAWAS_LIST,
        INVALID,
    }

    private Screen mCurrScreen;
    private ShikawasListFragment mShikawasListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mCurrScreen) {
                    case SHIKAWAS_LIST:
                        mShikawasListFragment.addShikawa(
                                new Shikawa("Shikawa Title", "Shikawa description goes here."));
                        break;
                }
            }
        });
        mCurrScreen = Screen.INVALID;
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(ARG_CURR_SCREEN)) {
                mCurrScreen = (Screen) savedInstanceState.getSerializable(ARG_CURR_SCREEN);
            }
        }
        if (mCurrScreen == Screen.INVALID) {
            //Shikawas list is the default fragment.
            mCurrScreen = Screen.SHIKAWAS_LIST;
        }
        showScreen(mCurrScreen);
    }

    private void showScreen(Screen screen) {
        mCurrScreen = screen;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (mShikawasListFragment == null) {
            mShikawasListFragment = ShikawasListFragment.newInstance();
            mShikawasListFragment.setRetainInstance(true);
        }

        int frame_id = R.id.frame_main_content;
        switch (screen) {
            case SHIKAWAS_LIST:
                fragmentTransaction.replace(frame_id, mShikawasListFragment);
                break;
            case INVALID:
                //unreachable!
                break;
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(ARG_CURR_SCREEN, mCurrScreen);
        super.onSaveInstanceState(outState);
    }
}
