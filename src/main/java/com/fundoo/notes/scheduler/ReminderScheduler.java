package com.fundoo.notes.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.fundoo.notes.repository.NoteRepository;
import com.fundoo.notes.entity.Note;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReminderScheduler {

    private final NoteRepository noteRepository;

    @Scheduled(fixedRate = 60000)
    public void sendReminders() {

        List<Note> notes = noteRepository.findDueReminders(LocalDateTime.now());

        for (Note note : notes) {
            System.out.println("🔔 Reminder for Note: " + note.getTitle());

            note.setReminderSent(true);
            noteRepository.save(note);
        }
    }
}