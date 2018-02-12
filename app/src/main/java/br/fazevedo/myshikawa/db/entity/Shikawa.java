package br.fazevedo.myshikawa.db.entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = RootReason.class,
                                  parentColumns = "id",
                                  childColumns = "shikawaId",
                                  onDelete = CASCADE))
public class Shikawa {
    @PrimaryKey(autoGenerate = true)
    public final long id;
    public final String description;

    public Shikawa(final long id, final String description) {
        this.id = id;
        this.description = description;
    }
}
