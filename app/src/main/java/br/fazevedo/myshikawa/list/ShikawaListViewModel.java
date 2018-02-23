package br.fazevedo.myshikawa.list;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

import br.fazevedo.myshikawa.db.ShikawaDatabase;
import br.fazevedo.myshikawa.db.entity.Shikawa;

public class ShikawaListViewModel extends GenericModelListViewModel<Shikawa> {
    public ShikawaListViewModel(@NonNull Application application) {
        super(application);

    }

    @Override
    protected LiveData<List<Shikawa>> createModelList() {
        return mDB.getShikawaDao().getAllShikawas();
    }

    @Override
    protected DaoInterface<Shikawa> createDaoInterface() {
        return new DaoInterface<Shikawa>() {
            @Override
            public void delete(Shikawa shikawa) {
                mDB.getShikawaDao().delete(shikawa);
            }

            @Override
            public void insert(Shikawa shikawa) {
                mDB.getShikawaDao().insert(shikawa);
            }
        };
    }
}
