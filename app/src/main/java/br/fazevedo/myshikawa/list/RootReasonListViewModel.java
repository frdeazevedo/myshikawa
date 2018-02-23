package br.fazevedo.myshikawa.list;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import br.fazevedo.myshikawa.db.entity.RootReason;
import br.fazevedo.myshikawa.db.entity.Shikawa;

public class RootReasonListViewModel extends GenericModelListViewModel<RootReason> {
    private Shikawa mShikawa;

    public RootReasonListViewModel(@NonNull Application application,
                                   Shikawa shikawa) {
        super(application);
        mShikawa = shikawa;
    }

    @Override
    protected LiveData<List<RootReason>> createModelList() {
        return mDB.getRootReasonDao().findRootReasons(mShikawa.id);
    }

    @Override
    protected DaoInterface<RootReason> createDaoInterface() {
        return new DaoInterface<RootReason>() {
            @Override
            public void delete(RootReason rootReason) {
                mDB.getRootReasonDao().delete(rootReason);
            }

            @Override
            public void insert(RootReason rootReason) {
                mDB.getRootReasonDao().insert(rootReason);
            }
        };
    }
}
