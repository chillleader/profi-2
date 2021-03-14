package ru.chillleader.restful.service;

import org.springframework.stereotype.Component;
import ru.chillleader.restful.model.Note;
import ru.chillleader.restful.model.NoteInput;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class InMemoryNoteStorage implements NoteStorage {

    private final Map<Long, Note> notes = new HashMap<>();

    private long idCounter = 0;

    @Override
    public synchronized long getNextId() {
        return idCounter++;
    }

    @Override
    public Note saveOne(Note note) {
        notes.put(note.getId(), note);
        return note;
    }

    @Override
    public Optional<Note> getOne(long id) {
        return Optional.ofNullable(notes.get(id));
    }

    @Override
    public List<Note> getAll() {
        return new ArrayList<>(notes.values());
    }

    @Override
    public Optional<Note> updateOne(Note note) {
        if (notes.containsKey(note.getId())) {
            notes.put(note.getId(), note);
            return Optional.of(note);
        }
        else return Optional.empty();
    }

    @Override
    public boolean deleteOne(long id) {
        if (!notes.containsKey(id)) return false;
        notes.remove(id);
        return true;
    }
}
