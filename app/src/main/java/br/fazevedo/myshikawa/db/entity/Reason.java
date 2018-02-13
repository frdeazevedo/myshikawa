package br.fazevedo.myshikawa.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Reason.class,
                                  parentColumns = "id",
                                  childColumns = "parentReasonId",
                                  onDelete = CASCADE))
public class Reason {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public final long parentReasonId;
    public final String description;

    public Reason(final long parentReasonId, final String description) {
        this.parentReasonId = parentReasonId;
        this.description = description;
    }
}