import java.io.*;

/**
 * Provides methods to save an object to a file and load an object from a file.
 */
public class ObjectSaverLoader {
    /**
     * Serializes and saves an object to a file.
     * @param object object to be saved
     * @param fileName file name
     * @throws IOException
     */
    public void saveObjectToFile(Object object, String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
    }

    /**
     * Loads and deserializes an object from a file.
     * @param fileName file name
     * @return an object from the file
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object loadObjectFromFile(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        return object;
    }
}