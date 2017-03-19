
public class Write extends Thread{
   private boolean status = true;
   private CacheManager cache = null;
   
   public Write(CacheManager c, String threadName) {
     this.cache = c;
     this.setName(threadName);
   }
   
   @Override
   public void run() {
     while (this.status) { 
       String [] key = cache.getKeys();
       synchronized (this) {
		
	
	       for (String k : key) {
	         String newValue = valueRetrieve(k);
	         
	         cache.setValue(k, newValue);
	      
	       }
	       notifyAll();
       }
       try {
         Thread.sleep(1000);
         
       } catch (InterruptedException e) {
         e.printStackTrace();
       }
     }
   }
   
   public void writeStop(){
     this.status = false;
     this.interrupt();
   }
   public String valueRetrieve(String k){
  
     return "newValue";
   }
 }