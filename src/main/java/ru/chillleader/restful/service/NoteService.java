package ru.chillleader.restful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.chillleader.restful.model.Note;
import ru.chillleader.restful.model.NoteInput;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private final NoteStorage noteStorage;

    private final int defaultHeaderLength;

    @Autowired
    public NoteService(NoteStorage storage,
                       @Value("${notes.default-header-length}") int defaultHeaderLength) {
        this.noteStorage = storage;
        this.defaultHeaderLength = defaultHeaderLength;
    }

    public Note storeOne(NoteInput input) {

        long generatedId = noteStorage.getNextId();
        Note note = new Note(input, generatedId);
        note.fillDefaultTitleIfNeeded(defaultHeaderLength);
        return noteStorage.saveOne(note);
    }

    public Note getOne(long id) {
        return noteStorage.getOne(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public List<Note> getAll(String query) {
        if (query != null) return findByQuery(query);
        return noteStorage.getAll();
    }

    public Note updateOne(long id, NoteInput noteInput) {
        noteInput.fillDefaultTitleIfNeeded(defaultHeaderLength);
        Note note = new Note(noteInput, id);
        return noteStorage.updateOne(note).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public List<Note> findByQuery(String query) {
        List<Note> allNotes = noteStorage.getAll();
        return allNotes.parallelStream()
                .filter(note -> note.getTitle().contains(query) || note.getContent().contains(query))
                .collect(Collectors.toList());
    }

    public void deleteOne(long id) {
        if (!noteStorage.deleteOne(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
