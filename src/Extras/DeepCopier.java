package Extras;

import java.io.ByteArrayInputStream;
import Model.Cell;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class DeepCopier {
	private DeepCopier() { }
	
	
	static public byte[] toByteStream(Object newObj) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);		
		
		oos.writeObject(newObj);
		oos.flush();
		oos.close();
		baos.close();
		
		return baos.toByteArray();
	}
	
	static public Object toObject(byte[] aByteStream) throws ClassNotFoundException, IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(aByteStream);
		return (Object) new ObjectInputStream(bais).readObject();
	}

}
