/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package is.zanzibar.ical.calendar.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.ValidationException;

/**
 *
 * @author ingimar
 */
public class Filehandler {

    public static String outFileName = "/home/ingimar/Desktop/Seminariet-kurser-kalender-out.ics";

    public static String inFileName = "/home/ingimar/Desktop/Seminariet-kurser-kalender.ics";

    public void writeToFile(Calendar calendar) {
        FileOutputStream fout = null;
        File file;

        try {

            file = new File(outFileName);
            fout = new FileOutputStream(file);


            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            CalendarOutputter outputter = new CalendarOutputter();
            outputter.output(calendar, fout);
           
            fout.flush();
            fout.close();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ValidationException ex) {
            Logger.getLogger(Filehandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fout != null) {
                    fout.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
