package br.fazevedo.myshikawa.db.entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Shikawa {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public final String description;

    public Shikawa(final String description) {
        this.description = description;
    }
}
