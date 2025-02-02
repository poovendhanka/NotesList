package com.notes.NotesList.repository;

import com.notes.NotesList.model.Task;
import com.notes.NotesList.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task , Long> {

    List<Task> findByUser(User user);
}
