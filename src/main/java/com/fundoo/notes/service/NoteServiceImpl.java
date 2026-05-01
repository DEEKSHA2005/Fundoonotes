package com.fundoo.notes.service;

import com.fundoo.notes.dto.NoteRequest;
import com.fundoo.notes.entity.Note;
import com.fundoo.notes.entity.User;
import com.fundoo.notes.repository.NoteRepository;
import com.fundoo.notes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    @Override
    public String createNote(NoteRequest request) {

        // Get logged-in user email from JWT
        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        // Fetch user
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create note
        Note note = new Note();
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        note.setUserId(user.getId());

        noteRepository.save(note);

        return "Note Created Successfully";
    }

    @Override
    public String updateNote(Long id, NoteRequest request) {

        // 🔥 get email from JWT
        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        // 🔥 fetch user
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔥 fetch note securely
        Note note = noteRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new RuntimeException("Note not found or access denied"));

        // 🔥 update fields
        if (request.getTitle() != null)
            note.setTitle(request.getTitle());

        if (request.getContent() != null)
            note.setContent(request.getContent());

        noteRepository.save(note);

        return "Note Updated Successfully";
    }

    @Override
    public String deleteNote(Long id) {

        // 🔥 get email from JWT
        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        // 🔥 fetch user
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔥 fetch note securely
        Note note = noteRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new RuntimeException("Note not found or access denied"));

        // 🔥 soft delete
        note.setTrashed(true);

        noteRepository.save(note);

        return "Note moved to trash";
    }

    @Override
    public List<Note> getNotes() {

        // 🔥 get email from JWT
        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        // 🔥 fetch user
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔥 fetch notes
        return noteRepository.findByUserId(user.getId());
    }

    @Override
    public Note getNoteById(Long id) {

        // 🔥 get email from JWT
        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        // 🔥 fetch user
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔥 fetch note securely
        return noteRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new RuntimeException("Note not found or access denied"));
    }
}