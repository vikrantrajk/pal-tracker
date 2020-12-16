package io.pivotal.pal.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;

@SpringBootApplication
public class PalTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PalTrackerApplication.class, args);
    }

    @Bean
    public TimeEntryRepository timeEntryRepository() {
        return new TimeEntryRepository() {
            @Override
            public TimeEntry create(TimeEntry timeEntry) {
                long timeEntryId = 1L;
                long projectId = 123L;
                long userId = 456L;
                TimeEntry expected = new TimeEntry(timeEntryId, projectId, userId, LocalDate.parse("2017-01-08"), 8);
                return expected;
            }

            @Override
            public TimeEntry find(long id) {
                long projectId = 123L;
                long userId = 456L;
                TimeEntry expected = new TimeEntry(id, projectId, userId, LocalDate.parse("2017-01-08"), 8);
                return expected;
            }

            @Override
            public List<TimeEntry> list() {
                List<TimeEntry> expected = asList(
                        new TimeEntry(1L, 123L, 456L, LocalDate.parse("2017-01-08"), 8)
                );
                return expected;
            }

            @Override
            public TimeEntry update(long id, TimeEntry timeEntry) {
                long projectId = 2L;
                long userId = 3L;
                TimeEntry timeEntryToUpdate = new TimeEntry(1L, projectId, userId, LocalDate.parse("2017-01-09"), 9);
                return timeEntryToUpdate;
            }

            @Override
            public void delete(long id) {
            }

        };
    }
}