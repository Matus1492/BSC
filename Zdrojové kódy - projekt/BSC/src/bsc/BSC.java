package bsc;

import java.io.IOException;
import java.util.Timer;

/**
 *
 * @author  Matus Rusinak
 * @version 1.0
 */
public class BSC extends Thread {


    /**
     * Main method of project. This method create a object of Exchange_app class and call methods on this object.
     * Method also create a Timer object for showing values of currencies to output every one minute.
     * 
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException{
        Exchange_app application = new Exchange_app();
        Timer timer = new Timer(); 
        
        application.load_file();
        timer.schedule(application, 60000, 60000);
        application.enter_values();
    }

}