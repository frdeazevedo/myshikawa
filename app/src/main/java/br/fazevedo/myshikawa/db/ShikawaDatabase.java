package br.fazevedo.myshikawa.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import br.fazevedo.myshikawa.db.dao.ReasonDao;
import br.fazevedo.myshikawa.db.dao.RootReasonDao;
import br.fazevedo.myshikawa.db.dao.ShikawaDao;
import br.fazevedo.myshikawa.db.entity.Reason;
import br.fazevedo.myshikawa.db.entity.RootReason;
import br.fazevedo.myshikawa.db.entity.Shikawa;

@Database(entities = {Shikawa.class, RootReason.class, Reason.class},
          version = 1)
public abstract class ShikawaDatabase extends RoomDatabase {
    private static final String DB_NAME = "shikawa.db";
    private static volatile ShikawaDatabase sInstance;

    static synchronized ShikawaDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = create(context);
        }
        return sInstance;
    }

    private static ShikawaDatabase create(final Context context) {
        return Room.databaseBuilder(context,
                ShikawaDatabase.class,
                DB_NAME).build();
    }

    public abstract ShikawaDao getShikawaDao();
    public abstract RootReasonDao getRootReasonDao();
    public abstract ReasonDao getReasonDao();
}
