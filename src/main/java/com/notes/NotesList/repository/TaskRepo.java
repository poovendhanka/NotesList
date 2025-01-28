package com.notes.NotesList.repository;

import com.notes.NotesList.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task , Long> {


}
