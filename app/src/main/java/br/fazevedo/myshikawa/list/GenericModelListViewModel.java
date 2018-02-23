package br.fazevedo.myshikawa.list;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

import br.fazevedo.myshikawa.db.ShikawaDatabase;

public abstract class GenericModelListViewModel<Model> extends AndroidViewModel {
    private LiveData<List<Model>> mModelList;
    private DaoInterface<Model> mDaoInterface;
    ShikawaDatabase mDB;

    GenericModelListViewModel(@NonNull Application application) {
        super(application);
        mDB = ShikawaDatabase.getInstance(application);
        mModelList = createModelList();
        mDaoInterface = createDaoInterface();
    }

    public LiveData<List<Model>> getModelList() {
        return mModelList;
    }
    public void delete(Model model) {
        new DaoAsyncTask<>(mDaoInterface, DaoAsyncTask.Action.DELETE)
                .execute(model);
    }
    public void insert(Model model) {
        new DaoAsyncTask<>(mDaoInterface, DaoAsyncTask.Action.INSERT)
                .execute(model);
    }

    protected abstract LiveData<List<Model>> createModelList();
    protected abstract DaoInterface<Model> createDaoInterface();

    static class DaoAsyncTask<Model> extends AsyncTask<Model, Void, Void> {
        enum Action {
            INSERT,
            DELETE
        }
        DaoInterface<Model> mDao;
        Action mAction;
        DaoAsyncTask(DaoInterface<Model> dao, Action action) {
            mDao = dao;
            mAction = action;
        }

        @Override
        protected Void doInBackground(Model... models) {
            for (Model m : models) {
                switch (mAction) {
                    case INSERT:
                        mDao.insert(m);
                        break;
                    case DELETE:
                        mDao.delete(m);
                        break;
                }
            }
            return null;
        }
    }

    protected interface DaoInterface<Model> {
        void delete(Model model);
        void insert(Model model);
    }
}
