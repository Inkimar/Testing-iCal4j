package is.zanzibar.ical.calendar.util;



/**
 *
 * @author ingimar
 */
public enum Ical {
    X_WR_CALNAME ("X-WR-CALNAME"),LAST_MODIFIED("LAST-MODIFIED"),
   UID,LOCATION, SEQUENCE,
   STATUS, SUMMARY,TRANSP,
   OPAQUE,CREATED, DTSTART,DTSTAMP,DTEND,DESCRIPTION;
   
   private final String name;       

    private Ical() {
        name ="";
    }

   
    private Ical(String s) {
        name = s;
    }
}
