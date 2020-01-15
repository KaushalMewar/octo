package com.octo.reminderservice.service.prod;

import com.octo.reminderservice.entity.Reminders;
import com.octo.reminderservice.notification.prod.SnsClient;
import com.octo.reminderservice.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Profile("prod")
@Service
public class SchedulingService {

    private ReminderRepository reminderRepository;

    @Autowired
    public SchedulingService(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    // runs post 5 hours from last execution
    @Scheduled(fixedDelay = 18000000)
    public void run() {

        System.out.println("Prod");
        LocalDate date = LocalDate.now();
        short day = (short)date.plusDays(1).getDayOfMonth();
        short month = (short)date.getMonthValue();

        Optional<List<Reminders>> reminders = reminderRepository.findByEventDayAndEventMonth(day,month);

        reminders.ifPresent(reminder -> {

            reminder.forEach(reminderInfo -> {
                String message = "Tomorrow is "+reminderInfo.getName()+" "+reminderInfo.getEventType()+".";
                System.out.println(message);
                SnsClient.sendSms(message);
            });

        });
    }
}
