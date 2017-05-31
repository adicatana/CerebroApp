package romaniancoder.booking;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adi_c on 31/05/2017.
 */
@Component
public class DatabaseSeeder implements CommandLineRunner {

    private BookingRepository bookingRepository;

    public DatabaseSeeder(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<HotelBooking> bookings = new ArrayList<>();
        bookings.add(new HotelBooking("Adi", 100, 4));
        bookings.add(new HotelBooking("Ioan", 50, 6));
        bookings.add(new HotelBooking("Andrei", 70, 8));

        bookingRepository.save(bookings);

    }
}
