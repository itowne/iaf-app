package newland.iaf.base.ftp;

import java.io.IOException;
import java.net.SocketException;

/**
 * 
 * @author rabbit
 * 
 */
public interface FtpService {
	String ftpGet(String remoteAddr, int remotePort, String remotePath,
			String remoteFileName, String localPath, String username,
			String password) throws SocketException, IOException;

}
