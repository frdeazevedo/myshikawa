package br.fazevedo.myshikawa.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.fazevedo.myshikawa.db.entity.Reason;

@Dao
public interface ReasonDao {
    @Insert
    void insert(Reason reason);

    @Update
    void update(Reason... reasons);

    @Delete
    void delete(Reason... reasons);

    @Query("SELECT * FROM reason WHERE parentReasonId=:parentReasonId")
    List<Reason> findReasons(final long parentReasonId);
}
