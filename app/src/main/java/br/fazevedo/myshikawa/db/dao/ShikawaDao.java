package br.fazevedo.myshikawa.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.fazevedo.myshikawa.db.entity.Shikawa;

@Dao
public interface ShikawaDao {
    @Insert
    void insert(Shikawa shikawa);

    @Update
    void update(Shikawa... shikawas);

    @Delete
    void delete(Shikawa... shikawas);

    @Query("SELECT * FROM shikawa")
    LiveData<List<Shikawa>> getAllShikawas();

    @Query("SELECT * FROM shikawa WHERE id=:shikawaId")
    Shikawa getShikawa(long shikawaId);
}
