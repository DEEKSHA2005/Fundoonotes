package com.fundoo.notes.controller;

import com.fundoo.notes.dto.NoteRequest;
import com.fundoo.notes.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public String createNote(@Valid @RequestBody NoteRequest request) {
        return noteService.createNote(request);
    }
}