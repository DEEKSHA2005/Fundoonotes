package com.fundoo.notes.batch;

import com.fundoo.notes.entity.Note;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class NoteProcessor implements ItemProcessor<NoteExcelDTO, Note> {

    @Override
    public Note process(NoteExcelDTO dto) {
        Note note = new Note();
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());
        return note;
    }
}