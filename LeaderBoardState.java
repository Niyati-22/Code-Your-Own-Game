import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

// This is an example of how to save and restore
// state to a file via serialization
public class LeaderBoardState implements Serializable {
  public HashMap<String, Integer> scores;
  private static final String fileName = "State.ser";

  public String toString () {
    return scores.toString();
  }

  // Save non-transient state to a local file
  // If "name" is "marc", then the local file we'll save the
  // state to will be called "marcState.ser"
  public boolean save () {
    try {
      FileOutputStream fos = new FileOutputStream(fileName);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(this);
      oos.close();
      fos.close();
      return true;
    } catch (IOException e) {
      System.err.println(e);
      return false;
    }
  }

  // Returns a State class from serialized state stored in the
  // file name + "State.ser", or null if unable to deserialize 
  public static LeaderBoardState restore () {
    try {
		  FileInputStream fis = new FileInputStream(fileName);
      ObjectInputStream ois = new ObjectInputStream(fis);
      LeaderBoardState s = (LeaderBoardState) ois.readObject();
	    ois.close();
	    fis.close();
      return s;
	  } catch(Exception e) { // IOException, ClassNotFoundException
	    return null;
	  }
  }
}