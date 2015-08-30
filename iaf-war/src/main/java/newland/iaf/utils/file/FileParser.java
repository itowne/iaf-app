package newland.iaf.utils.file;
import java.io.File;
/**
 * IFileParser
 * Project: CashManagement
 * @author shen
 * 
 * <p>对于临时生成的文件，在文件生成后，请即时删除文件实体。</p>
 *
 * 2004-7-19
 */
public interface FileParser <T> {
    /**
     * <p>将对象转换为文件</p>
     * <p>生成文件将放置在系统的临时文件夹</p>
     * 
     * <p>对于临时生成的文件，在文件生成后，请及时删除文件实体。</p>
     * 
     * @param object 要转换的对象
     * @return
     */
    public File convertToFile(T object);
    
    /**
     * 将对象转换为文件
     * <p>对于临时生成的文件，在文件生成后，请及时删除文件实体。</p>
     * @param object 要转换的对象
     * @param fileName 文件名
     * @return
     */
    public File convertToFile(T object, String filePath);
    
    /**
     * 将文件内容转换为对象
     * @param file
     * @return
     */
    public T convertFromFile(File file);

    /**
     * 将文件内容转换为对象
     * @param path
     * @return
     */
    public T convertFromFile(String filePath);
}
