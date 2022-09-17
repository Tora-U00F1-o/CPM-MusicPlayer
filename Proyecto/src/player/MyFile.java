package player;

import java.io.*;
import java.util.Objects;

public class MyFile {
	private File f;
	
	public MyFile (File f){
		this.f = f;
	}
	
	public File getF() {
		return f;
	}

	public String toString() {
		return f.getName();
	}

	@Override
	public int hashCode() {
		return Objects.hash(f);
	}

	public boolean isEqualsFile(File other) {
		if (other == null)
			return false;
		
		return Objects.equals(f, other);
	}
	
}