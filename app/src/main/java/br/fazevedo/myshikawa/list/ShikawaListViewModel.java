package br.fazevedo.myshikawa.list;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.nio.channels.AsynchronousChannelGroup;
import java.util.List;

import br.fazevedo.myshikawa.db.ShikawaDatabase;
import br.fazevedo.myshikawa.db.entity.Shikawa;

public class ShikawaListViewModel extends AndroidViewModel {

    private final LiveData<List<Shikawa>> mShikawasList;
    private ShikawaDatabase mDB;

    public ShikawaListViewModel(@NonNull Application application) {
        super(application);

        mDB = ShikawaDatabase.getInstance(this.getApplication());
        mShikawasList = mDB.getShikawaDao().getAllShikawas();
    }

    public LiveData<List<Shikawa>> getShikawasList() {
        return mShikawasList;
    }

    public void deleteShikawa(Shikawa shikawa) {
    }

    static class DeleteAsyncTask extends AsyncTask<Shikawa, Void, Void> {

        ShikawaDatabase mDB;
        DeleteAsyncTask(ShikawaDatabase db) {
            mDB = db;
        }


        @Override
        protected Void doInBackground(Shikawa... shikawas) {
            mDB.getShikawaDao().delete(shikawas);
            return null;
        }
    }
}
