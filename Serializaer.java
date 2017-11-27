package lab5;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Collection;

public interface Serializaer<T> {
	void serialize(T object, File output) throws IOException;
    void serializeCollection(List<T> objects, File output) throws IOException;
    T deserialize(File input) throws IOException;
    List<Product> deserializeCollection(File input) throws IOException;
	
}