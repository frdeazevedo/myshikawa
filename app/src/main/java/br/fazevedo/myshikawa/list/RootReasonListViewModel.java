package br.fazevedo.myshikawa.list;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

import br.fazevedo.myshikawa.db.ShikawaDatabase;
import br.fazevedo.myshikawa.db.entity.RootReason;
import br.fazevedo.myshikawa.db.entity.Shikawa;

public class RootReasonListViewModel extends AndroidViewModel {

    private final LiveData<List<RootReason>> mRootReasonList;
    private ShikawaDatabase mDB;

    public RootReasonListViewModel(@NonNull Application application,
                                   Shikawa shikawa) {
        super(application);

        mDB = ShikawaDatabase.getInstance(this.getApplication());
        mRootReasonList = mDB.getRootReasonDao().findRootReasons(shikawa.id);
    }

    public void deleteRootReason(RootReason reason) {
        new DeleteAsyncTask(mDB).execute(reason);
    }

    public void addRootReason(RootReason reason) {
        new AddAsyncTask(mDB).execute(reason);
    }

    public LiveData<List<RootReason>> getRootReasons() {
        return mRootReasonList;
    }

    static class DeleteAsyncTask extends AsyncTask<RootReason, Void, Void> {
        ShikawaDatabase mDB;
        DeleteAsyncTask(ShikawaDatabase db) {
            mDB = db;
        }

        @Override
        protected Void doInBackground(RootReason... reasons) {
            mDB.getRootReasonDao().delete(reasons);
            return null;
        }
    }

    static class AddAsyncTask extends AsyncTask<RootReason, Void, Void> {
        ShikawaDatabase mDB;
        AddAsyncTask(ShikawaDatabase db) {
            mDB = db;
        }

        @Override
        protected Void doInBackground(RootReason... reasons) {
            mDB.getRootReasonDao().insert(reasons[0]);
            return null;
        }
    }
}
