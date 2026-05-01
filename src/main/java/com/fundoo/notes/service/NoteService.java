package com.fundoo.notes.service;

import com.fundoo.notes.dto.NoteRequest;

public interface NoteService {
    String createNote(NoteRequest request);
}