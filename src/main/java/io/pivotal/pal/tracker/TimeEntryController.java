package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntry) {
        TimeEntry newTimeEntry = timeEntryRepository.create(timeEntry);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTimeEntry);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id) {
        TimeEntry timeEntry = timeEntryRepository.find(id);
        if (timeEntry == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(timeEntry);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return ResponseEntity.ok(timeEntryRepository.list());
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody TimeEntry timeEntryToUpdate) {
        TimeEntry timeEntry = timeEntryRepository.update(id, timeEntryToUpdate);
        if (timeEntry == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(timeEntry);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        timeEntryRepository.delete(id);
        return ResponseEntity.noContent().build();
    }

}
