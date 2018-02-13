package br.fazevedo.myshikawa.db.entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Shikawa {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public final String description;
    public final String title;

    public Shikawa(final String title, final String description) {
        this.title = title;
        this.description = description;
    }
}
