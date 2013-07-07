package is.zanzibar.ical.calendar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    private final String calendarName = "/home/ingimar/Desktop/Seminariet-kurser-kalender.ics";

    public static void main(String[] args) {
        System.out.println("Testing ical4j");

        App app = new App();
        Calendar calendar = app.readCalendar();
        app.iterateOverCal(calendar);
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
        }
        return calendar;
    }

    private void iterateOverCal(Calendar calendar) {
        Map<String, List<String>> map = new HashMap<>();
        for (Iterator i = calendar.getComponents().iterator(); i.hasNext();) {
            Component component = (Component) i.next(); // for every event ...
            System.out.println("Component [" + component.getName() + "]");
            

            for (Iterator j = component.getProperties().iterator(); j.hasNext();) {
                Property property = (Property) j.next();
                System.out.println("Property [" + property.getName() + ", " + property.getValue() + "]");
            }
        }
    }
}
