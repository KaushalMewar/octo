package com.octo.reminderservice.service.dev;

import com.octo.reminderservice.entity.Reminders;
import com.octo.reminderservice.notification.dev.DevSnsClient;
import com.octo.reminderservice.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Profile("dev")
@Service
public class DevSchedulingService {

    private ReminderRepository reminderRepository;

    @Autowired
    public DevSchedulingService(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    @Scheduled(fixedDelay = 60000)
    public void run() {

        System.out.println("Dev");
        LocalDate date = LocalDate.now();
        short day = (short)date.plusDays(1).getDayOfMonth();
        short month = (short)date.getMonthValue();

        Optional<List<Reminders>> reminders = reminderRepository.findByEventDayAndEventMonth(day,month);

        reminders.ifPresent(reminder -> {

            reminder.forEach(reminderInfo -> {
                String message = "Tomorrow is "+reminderInfo.getName()+" "+reminderInfo.getEventType()+".";
                System.out.println(message);
                DevSnsClient.sendSms(message);
            });
        });
    }
}