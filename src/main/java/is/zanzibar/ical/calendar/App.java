package is.zanzibar.ical.calendar;

import is.zanzibar.ical.calendar.util.Filehandler;
import is.zanzibar.ical.calendar.util.Ical;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;

/**
 * Hello world!
 *
 */
public class App {

    private final String calendarName = Filehandler.inFileName;//"/home/ingimar/Desktop/Seminariet-kurser-kalender.ics";

    public static void main(String[] args) {
        System.out.println("Testing ical4j");

        App app = new App();
        Calendar calendar = app.readCalendar();
        int eventsInCal = app.getNumberOfEventsInCalendar(calendar);
        System.out.println("Number of Events : " + eventsInCal);
        String calendarName = app.getCalendarName(calendar);
        System.out.println("Name of Calendar : " + calendarName);

        try {
            Calendar updatedCal = app.iterateOverCal(calendar); // test to updata a field
            app.iterateOverCal(updatedCal);
            Filehandler handler = new Filehandler();
            handler.writeToFile(calendar);

        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }



    }

    private Calendar readCalendar() {

        FileInputStream fin = null;
        try {
            fin = new FileInputStream(calendarName);
            System.out.println("Found Calendar");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

        CalendarBuilder builder = new CalendarBuilder();
        Calendar calendar = null;
        try {
            calendar = builder.build(fin);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fin.close();
            } catch (IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return calendar;
    }

    private String getCalendarName(Calendar calendar) {
        Property property = calendar.getProperty("X-WR-CALNAME");
        String nameOfCalender = property.getValue();

        return nameOfCalender;
    }

    private Calendar iterateOverCal(Calendar calendar) throws Exception {

        Map<String, List<String>> map = new HashMap<>();
        for (Iterator i = calendar.getComponents().iterator(); i.hasNext();) {
            Component component = (Component) i.next(); // for every event ...
            System.out.println("Component [" + component.getName() + "]");


            for (Iterator j = component.getProperties().iterator(); j.hasNext();) {
                Property property = (Property) j.next();
                if (property.getName().equals(Ical.LOCATION.toString())) {
                    property.setValue("New Value - Järna");
                }
                System.out.println("Property [" + property.getName() + ", " + property.getValue() + "]");
            }
        }
        return calendar;
    }

    private int getNumberOfEventsInCalendar(Calendar calendar) {
        int size = calendar.getComponents().size();
        return size;
    }
}
