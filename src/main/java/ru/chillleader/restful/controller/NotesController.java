package ru.chillleader.restful.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.chillleader.restful.model.Note;
import ru.chillleader.restful.model.NoteInput;
import ru.chillleader.restful.service.NoteService;

import java.util.List;

@Api("Notes REST API")
@RestController
@RequestMapping("/notes")
public class NotesController {

    private final NoteService noteService;

    @Autowired
    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    @ApiOperation("Сохранить новую заметку")
    @PostMapping
    public Note storeNew(@RequestBody NoteInput noteInput) {
        return noteService.storeOne(noteInput);
    }

    @ApiOperation("Получить список всех заметок, удовлетворяющих опциональному запросу")
    @GetMapping
    public List<Note> getAll(@RequestParam(name = "query", required = false) String query) {
        return noteService.getAll(query);
    }

    @ApiOperation("Получить заметку по ID")
    @GetMapping("/{id}")
    public Note getOne(@PathVariable long id) {
        return noteService.getOne(id);
    }

    @ApiOperation("Редактировать заметку по ID")
    @PutMapping("/{id}")
    public Note editOne(@PathVariable long id, @RequestBody NoteInput noteInput) {
        return noteService.updateOne(id, noteInput);
    }

    @ApiOperation("Удалить заметку по ID")
    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable long id) {
        noteService.deleteOne(id);
    }
}
