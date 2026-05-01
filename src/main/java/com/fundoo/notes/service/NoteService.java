package com.fundoo.notes.service;

import com.fundoo.notes.dto.NoteRequest;
import com.fundoo.notes.entity.Note;
import java.util.List;


public interface NoteService {
    String createNote(NoteRequest request);
    String updateNote(Long id, NoteRequest request);
    String deleteNote(Long id);
    List<Note> getNotes();
    Note getNoteById(Long id);
}