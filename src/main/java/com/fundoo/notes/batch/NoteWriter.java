package com.fundoo.notes.batch;

import com.fundoo.notes.entity.Note;
import com.fundoo.notes.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoteWriter implements ItemWriter<Note> {

    private final NoteRepository noteRepository;

    @Override
    public void write(Chunk<? extends Note> items) {
        noteRepository.saveAll(items.getItems());
    }
}