package br.fazevedo.myshikawa.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import br.fazevedo.myshikawa.db.entity.Reason;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Reason.class,
                                  parentColumns = "id",
                                  childColumns = "parentReasonId",
                                  onDelete = CASCADE))
public class RootReason {
    @PrimaryKey(autoGenerate = true)
    public final long id;
    public final long shikawaId;
    public final String description;

    public RootReason(final long id, final long shikawaId, final String description) {
        this.id = id;
        this.shikawaId = shikawaId;
        this.description = description;
    }
}
