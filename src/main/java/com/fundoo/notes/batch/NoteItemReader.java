package com.fundoo.notes.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class NoteItemReader implements ItemReader<NoteExcelDTO> {

    private Iterator<NoteExcelDTO> iterator;

    public NoteItemReader() {
        List<NoteExcelDTO> list = new ArrayList<>();

        NoteExcelDTO n1 = new NoteExcelDTO();
        n1.setTitle("Batch Note 1");
        n1.setContent("Batch Content 1");

        list.add(n1);

        iterator = list.iterator();
    }

    @Override
    public NoteExcelDTO read() {
        return iterator.hasNext() ? iterator.next() : null;
    }
}