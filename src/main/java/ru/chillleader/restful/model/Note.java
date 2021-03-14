package ru.chillleader.restful.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Note extends NoteInput {

    private final long id;

    public Note(NoteInput input, long id) {
        this.id = id;
        this.title = input.title;
        this.content = input.content;
    }

}
