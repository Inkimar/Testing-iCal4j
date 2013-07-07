package is.zanzibar.ical.calendar.util;

/**
 *
 * @author ingimar
 */
public enum Ical {

    X_WR_CALNAME("X-WR-CALNAME"), LAST_MODIFIED("LAST-MODIFIED"),
    SUMMARY("Rubriken"), DESCRIPTION("Beskrivningen"),
    UID, LOCATION, SEQUENCE, STATUS, TRANSP,
    OPAQUE, CREATED, DTSTART, DTSTAMP, DTEND;

    private final String name;

    private Ical() {
        name = "";
    }

    private Ical(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }
}
