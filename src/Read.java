
public class Read extends Thread{
   
  private CacheManager cache = null;
   public Read(CacheManager c, String threadName) {
     this.cache = c;
     this.setName(threadName);
   }
   
   private boolean status = true;
   @Override
   public void run() {
     while (status) {
      String [] key = cache.getKeys();
       for (String k : key) {
         String value = cache.getValue(k);
       
         System.out.println(k + " : " + value);
       }
       
       
       try {
         Thread.sleep(1000);
       } catch (InterruptedException e) {
         e.printStackTrace();
       }
     }
   }
   
   public void readerStop(){
     this.status = false;
     this.interrupt();
   }
 }