package bsc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.TimerTask;

/**
 *
 * @author  Matus Rusinak
 * @version 1.0
 */
public class Exchange_app extends TimerTask{
    
    private int indicator = 0;
    private String currency;
    private int amount;
    private final Hashtable<String, Integer> hs_cur_amo = new Hashtable<>();
    private final String REGEX = "[A-Z]{3}\\s[-]?\\d+";
    
    /**
     * Method for loading data from file and store it to hash table. 
     * If data stored in file has invalid input format at some line, method show a warning message with index of this line.
     * If user enter a different option as is requested (Y/N), method show a warning message.
     * If user enters 'quit', program will be ended.
     * 
     * @throws java.io.FileNotFoundException    Show a warning message when user enter a non-existing filename.
     * @throws java.io.IOException
     */
    public void load_file() throws FileNotFoundException, IOException{
        while(indicator<1){
            System.out.println("Do you want to load data from file? Y/N");
            Scanner in = new Scanner(System.in);
            String qstn_answ = in.next();
            
            switch(qstn_answ.toUpperCase()){
                case "Y":
                    indicator++;
                    System.out.println("Enter the filename:");
                
                    String line;
                    int count=0;
                
                    try {
                        InputStream fis = new FileInputStream(in.next());
                        InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
                        BufferedReader br = new BufferedReader(isr);
                        
                            while ((line = br.readLine()) != null) {
                                count++;
                                if(line.matches(REGEX)){
                                    currency = line.substring(0,3);
                                    amount = Integer.parseInt(line.substring(4));
                
                                    if(hs_cur_amo.containsKey(currency)){
                                        hs_cur_amo.put(currency,amount + hs_cur_amo.get(currency));               
                                    }
                                    else{
                                        hs_cur_amo.put(currency, amount);
                                    }
                                }
                                else{
                                    System.out.println("### Error! Wrong input format at line "+count+"! ###");
                                }
                            }
                        }
                    catch(FileNotFoundException e){
                        System.out.println("### Error! File does not exist! ###");
                    }
                    break;
                case "N":
                    indicator++;
                    break;
                case "QUIT":
                    System.exit(0);
                default: 
                    System.out.println("### Error! Wrong input ... try again! ###");
                    break;
                }
        }
    }
    
    /**
     * Method for loading data from input. 
     * If user enter record in invalid format, method show a warning message.
     * If user enters 'quit', program will be ended.
     */
    public void enter_values(){
        System.out.println("Enter the values:");
        
        while(indicator>=1){
            Scanner in = new Scanner(System.in);
            String row = in.nextLine(); 
            
            if(row.equalsIgnoreCase("quit")){
                System.exit(0);
            }
            
            if(row.matches(REGEX)){
                currency = row.substring(0,3);
                amount = Integer.parseInt(row.substring(4));
                
                if(hs_cur_amo.containsKey(currency)){
                    hs_cur_amo.put(currency,amount + hs_cur_amo.get(currency));               
                }
                else{
                    hs_cur_amo.put(currency, amount);
                }
            }
            else{
                System.out.println("### Error! Wrong input format! ###");
            }
        }
    }
    
    /**
     * Function for showing a current amounts of each stored currencies. Method converts stored amounts to USD, too.
     * Override the Timer run method.
     * If hash table is empty, no data will be shown. If table is not empty, all stored amounts will be shown every one minute.
     */
    @Override
    public void run(){
        Enumeration e = hs_cur_amo.keys();
        String currencies;
        String exchange;
        BigDecimal convert_to_USD, roundOff;
        
        if(!hs_cur_amo.isEmpty()){
  
            System.out.println();
            System.out.println("Current amounts of each currency:");
        
            while(e.hasMoreElements()) {
                currencies = e.nextElement().toString();
            
                if(hs_cur_amo.get(currencies)!= 0){
                    switch(currencies){
                        case "EUR": 
                            convert_to_USD = new BigDecimal(hs_cur_amo.get(currencies)*1.1480); 
                            roundOff = convert_to_USD.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                            exchange = " (USD "+roundOff+")"; 
                            break;
                        case "GBP":
                            convert_to_USD = new BigDecimal(hs_cur_amo.get(currencies)*1.3057); 
                            roundOff = convert_to_USD.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                            exchange = " (USD "+roundOff+")";
                            break;
                        case "HKD":
                            convert_to_USD = new BigDecimal(hs_cur_amo.get(currencies)*0.1281); 
                            roundOff = convert_to_USD.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                            exchange = " (USD "+roundOff+")";
                            break;
                        case "RMB":
                            convert_to_USD = new BigDecimal(hs_cur_amo.get(currencies)*0.1477); 
                            roundOff = convert_to_USD.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                            exchange = " (USD "+roundOff+")";
                            break;
                        default:
                            exchange = "";
                            break;
                    }
                    System.out.println(currencies+" "+hs_cur_amo.get(currencies)+exchange);
                }
            }
            System.out.println();
        }
    }
    
    
}
