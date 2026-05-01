package com.fundoo.notes.controller;

import com.fundoo.notes.dto.NoteRequest;
import com.fundoo.notes.entity.Note;
import com.fundoo.notes.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public String createNote(@Valid @RequestBody NoteRequest request) {
        return noteService.createNote(request);
    }

    @GetMapping
    public List<Note> getNotes() {
        return noteService.getNotes();
    }

    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id);
    }

    @PutMapping("/{id}")
    public String updateNote(@PathVariable Long id,
                             @RequestBody NoteRequest request) {
        return noteService.updateNote(id, request);
    }
}