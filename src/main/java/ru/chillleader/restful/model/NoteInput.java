package ru.chillleader.restful.model;

import lombok.Data;

@Data
public class NoteInput {

    protected String title;

    protected String content;

    public void fillDefaultTitleIfNeeded(int titleLength) {
        if (title == null || title.isBlank()) {
            title = content.substring(0, Math.min(titleLength, content.length()));
        }
    }
}
