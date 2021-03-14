package ru.chillleader.restful.service;

import ru.chillleader.restful.model.Note;
import ru.chillleader.restful.model.NoteInput;

import java.util.List;
import java.util.Optional;

public interface NoteStorage {

    long getNextId();

    Note saveOne(Note note);

    Optional<Note> getOne(long id);

    List<Note> getAll();

    Optional<Note> updateOne(Note note);

    boolean deleteOne(long id);
}
