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
}