
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import com.opencsv.exceptions.CsvException;

public class catalog_service_impl implements catalog_service {
	private static HashMap<Integer, book> bookMap;
	String filePath = "book.csv";
	book newbook = new book();
	String booktopic;
	
	 static List<book> books = new ArrayList<book>();
	
    public catalog_service_impl() {
    	
    	bookMap = new HashMap<>();
    	readCsv(filePath);
    }
	@Override
	public Collection<book> getbooks(String topic) {
	
		HashMap<Integer, book> bookMap2 = new HashMap<>();
		
		 for(int i=1;i<=4;i++) {
			 if(topic.equals(bookMap.get(i).getTopic())) {
				 bookMap2.put(i, bookMap.get(i));
			 }
		 }

		 return bookMap2.values();
		 
	}

	@Override
	public book getbook(int id) {
		// TODO Auto-generated method stub
		//readCsv(filePath);
		 return bookMap.get(id);
	}
	
	
	
	
	
	
	@Override
	public book update(book k, String id) throws UserException {
		
		 try {
	            if (id == null)
	                throw new UserException("ID cannot be blank");

	            book kk = bookMap.get(k.getItem_number());

	            if (kk == null)
	              return kk;
	            
	            if (k.getCost() != 0.0) {
	                kk.setCost(k.getCost());
	            }
	            if (k.getBook_count () !=0) {
	            	System.out.print(k.getBook_count());
	                kk.setBook_count(k.getBook_count()+kk.getBook_count());
	            }
	            updatecsv(filePath,kk);
	            
	            return kk;
	        }
		 catch (Exception ex) {
	            throw new UserException(ex.getMessage());
	        }
	}
	
	 public static void readCsv(String filePath) {
		  BufferedReader reader = null;
		  
		  try {
		 //  List<book> books = new ArrayList<book>();
		   String line = "";
		   reader = new BufferedReader(new FileReader(filePath));
		   reader.readLine();
		   
		   while((line = reader.readLine()) != null) {
		    String[] fields = line.split(",");
		    
		    if(fields.length > 0) {
		     book book = new book();
		     book.setItem_number(Integer.parseInt(fields[0]));
		     book.setBook_title(fields[1]);
		     book.setTopic(fields[2]);
		     book.setCost(Double.parseDouble(fields[3]));
		     book.setBook_count(Integer.parseInt(fields[4]));
		     bookMap.put(book.getItem_number(), book);
		     
		     books.add(book);
		    }
		   }
		   
		   reader.close();
		   
		  } catch (Exception ex) {
		   ex.printStackTrace();
		  } finally {
		   try {
		    reader.close();
		   } catch (Exception e) {
		    e.printStackTrace();
		   }
		  }
		  
		 }
	 public static void updatecsv(String pathfile , book k ) throws IOException, CsvException {
		
		  for (book bb : books){
			  if(bb.getItem_number() == k.getItem_number()) {
				  
				  books.set(bb.getItem_number()-1, k);
			  }
			  
			  
		  }
		  FileWriter fileWriter = null;
		  try {
		   fileWriter = new FileWriter(pathfile);
		   
		   
		   fileWriter.append("item_number, book_title, topic, price, quantity\n");
		   for(book b: books) {
			   fileWriter.write(String.valueOf(b.getItem_number()));
			    fileWriter.append(",");
			    fileWriter.write(b.getBook_title());
			    fileWriter.append(",");
			    fileWriter.write(b.getTopic());
				 fileWriter.append(",");
				  fileWriter.write(String.valueOf(b.getCost()));
				   fileWriter.append(",");
				   fileWriter.write(String.valueOf(b.getBook_count()));
				 
			    fileWriter.append("\n");
		   }
		 
		  } catch (Exception ex) {
		   ex.printStackTrace();
		  } finally {
		   try {
		    fileWriter.flush();
		    fileWriter.close();
		   } catch (Exception e) {
		    e.printStackTrace();
		   }
		  }
		 }
	 
	 
	@Override
	public boolean isExist(int id) {
		// TODO Auto-generated method stub
		
	        return bookMap.containsKey(id);
	    
		
	}
}
