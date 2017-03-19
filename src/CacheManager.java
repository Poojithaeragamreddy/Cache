 
 import java.util.HashMap;
 import java.util.concurrent.locks.Lock;
 import java.util.concurrent.locks.ReentrantReadWriteLock;
 
 public class CacheManager {
   
   private final ReentrantReadWriteLock readWriteCache = 
     new ReentrantReadWriteLock();
 
   private final Lock reading  = readWriteCache.readLock();
   
   private final Lock writing = readWriteCache.writeLock();
   
   private HashMap<String, String> cache = new HashMap<String, String>();
   
   public void setValue(String key, String value) {
     writing.lock();
     try {
       cache.put(key, value);
     } finally {
       writing.unlock();
     }
   }
   
   public String getValue(String key) {
     reading.lock();
     try{
       return cache.get(key);
     } finally {
       reading.unlock();
     }
   }
 
   public String[] getKeys(){
     reading.lock();
     try{
       String keys[] = new String[cache.size()];
       return cache.keySet().toArray(keys);
     } finally {
       reading.unlock();
     }
   }
   
   public static void main(String[] args) {
	   CacheManager cache = new CacheManager();
     cache.setValue("object1",  "sjsu");
     cache.setValue("object2", "sdsu");
     Write write1  = new Write(cache, "student1");
     
     Read read1 = new Read(cache ,"student1");
     Read read2 = new Read(cache ,"student2");
     Read read3 = new Read(cache ,"student3");
     Read read4 = new Read(cache ,"student4");
     write1.start();
     read1.start();
     read2.start();
     read3.start();
     read4.start();
   }
   
 }