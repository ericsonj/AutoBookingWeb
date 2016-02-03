package test.cronjobs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import test.dao.CarDateDao;
import test.elements.CarDate;

/**
 *
 * @author ejoseph
 */
public class TaskDate implements ServletContextListener{

    private Thread t = null;
    private ServletContext context;
    private long expireTime = 15;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        t =  new Thread(){
            //task
            public void run(){                
                try {
                    while(true){
                        System.out.println("Thread running every second");
                        Thread.sleep(10000);
                        long current = System.currentTimeMillis() - (expireTime*60*1000);
                        Date date = new Date(current);
                        System.out.println(">>>>>>"+dateFormat.format(date));
                        CarDateDao carDateDao = new CarDateDao();
                        List<CarDate> carDate = carDateDao.findByDateLast(date);
                        for (CarDate carDate1 : carDate) {
                            carDate1.setState("FINISH");
                            carDateDao.edit(carDate1);
                            System.out.println(carDate1.toString());
                        }
                    }
                } catch (InterruptedException e) {}
            }            
        };
        t.start();
        context = sce.getServletContext();
        // you can set a context variable just like this
        context.setAttribute("TEST", "TEST_VALUE");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        t.interrupt();
    }
    
}
