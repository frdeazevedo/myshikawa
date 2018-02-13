package br.fazevedo.myshikawa.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.fazevedo.myshikawa.db.entity.RootReason;

@Dao
public interface RootReasonDao {
    @Insert
    void insert(RootReason rootReason);

    @Update
    void update(RootReason... rootReasons);

    @Delete
    void delete(RootReason... rootReasons);

    @Query("SELECT * FROM rootreason WHERE shikawaId=:shakawaId")
    LiveData<List<RootReason>> findRootReasons(final long shakawaId);
}
