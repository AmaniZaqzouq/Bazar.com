
import static spark.Spark.*;
import com.google.gson.Gson;

public class spark_main {
	
	public static void main(String[] args) {
		
		final catalog_service catalogService = new catalog_service_impl();
		Gson gson = new Gson();
		port(8080);
		get("/search/:topic", (request, response) -> {
            response.type("application/json");
            
            catalogService.getbooks( request.params(":topic"));
            
            
            
            
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, gson.toJsonTree(catalogService.getbooks( request.params(":topic")))));
        });
		
		
		 get("/lookup/:id", (request, response) -> {
	            response.type("application/json");
                    book nbook =catalogService.getbook(Integer.parseInt(request.params(":id")));
                    if(nbook != null) {
	            return new Gson().toJson
	            		(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(nbook)));
	            		}
	         else {
			 
			 return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, new Gson().toJson("book not found or error in id")));
			 
		 } });
		
		 
		 
		 
		 put("/update/:id", (request, response) -> {
	            response.type("application/json");
	           
	            book toEdit = new Gson().fromJson(request.body(), book.class);
	            
	            book editedbook = catalogService.update(toEdit , request.params(":id"));

	            if (editedbook != null) {
	                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(editedbook)));
	            } else {
	                return new Gson().toJson
	                		(new StandardResponse(StatusResponse.ERROR, new Gson().toJson("book not found or error in id")));
	            }
	        });
		 
		 get("/isexist/:id", (request, response) -> {
	            response.type("application/json");

	            return new Gson().toJson(catalogService.isExist(Integer.parseInt(request.params(":id"))) ? "book exists" : "book does not exist");
	        });
		
		get("/hello", (req, res)->"Hello dddddddddd   world");
	}

}
