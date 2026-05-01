package com.fundoo.notes.service;

import com.fundoo.notes.dto.NoteRequest;
import com.fundoo.notes.entity.Note;
import com.fundoo.notes.entity.User;
import com.fundoo.notes.repository.NoteRepository;
import com.fundoo.notes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private static final Logger log = LoggerFactory.getLogger(NoteServiceImpl.class);

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final RabbitMQProducer producer;

    // ================= CREATE NOTE =================
    @Override
    public String createNote(NoteRequest request) {

        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        log.info("Creating note for user: {}", email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.error("User not found: {}", email);
                    return new RuntimeException("User not found");
                });

        Note note = new Note();
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        note.setUserId(user.getId());

        noteRepository.save(note);

        log.info("Note created successfully for userId: {}", user.getId());

        producer.sendMessage("New note created by: " + user.getEmail());

        return "Note Created Successfully";
    }

    // ================= UPDATE NOTE =================
    @Override
    public String updateNote(Long id, NoteRequest request) {

        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        log.info("Updating note id: {} for user: {}", id, email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Note note = noteRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new RuntimeException("Note not found or access denied"));

        if (request.getTitle() != null)
            note.setTitle(request.getTitle());

        if (request.getContent() != null)
            note.setContent(request.getContent());

        noteRepository.save(note);

        log.info("Note updated successfully: {}", id);

        return "Note Updated Successfully";
    }

    // ================= DELETE NOTE =================
    @Override
    public String deleteNote(Long id) {

        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        log.info("Deleting note id: {} for user: {}", id, email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Note note = noteRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new RuntimeException("Note not found or access denied"));

        note.setTrashed(true);

        noteRepository.save(note);

        log.info("Note moved to trash: {}", id);

        return "Note moved to trash";
    }

    // ================= GET ALL NOTES =================
    @Override
    public List<Note> getNotes() {

        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        log.info("Fetching notes for user: {}", email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return noteRepository.findByUserId(user.getId());
    }

    // ================= GET NOTE BY ID =================
    @Override
    public Note getNoteById(Long id) {

        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        log.info("Fetching note id: {} for user: {}", id, email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return noteRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new RuntimeException("Note not found or access denied"));
    }
}