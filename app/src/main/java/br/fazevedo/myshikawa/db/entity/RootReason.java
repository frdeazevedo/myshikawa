package br.fazevedo.myshikawa.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import br.fazevedo.myshikawa.db.entity.Reason;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Reason.class,
                                  parentColumns = "id",
                                  childColumns = "shikawaId",
                                  onDelete = CASCADE))
public class RootReason {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public final long shikawaId;
    public final String description;
    public final int status; //0: not done, 1: on going, 2: done

    public RootReason(final long shikawaId, final String description, final int status) {
        this.shikawaId = shikawaId;
        this.description = description;
        this.status = status;
    }
}
