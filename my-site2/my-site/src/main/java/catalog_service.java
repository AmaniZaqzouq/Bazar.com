import java.util.Collection;

public interface catalog_service {

	public Collection<book> getbooks(String topic);

    public book getbook(int id);
    
    public book update(book k , String id) throws UserException;
    public boolean isExist(int id);
}
