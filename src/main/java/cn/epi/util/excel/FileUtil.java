package cn.epi.util.excel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import cn.epi.common.config.JConfig;
import cn.epi.common.utils.JStringUtils;

public class FileUtil {
	 public  File save(MultipartFile file,HttpServletRequest request) throws Exception{
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
			String res = sdf.format(new Date());
		 // 获取本地存储路径
	        String path = request.getSession().getServletContext()
	                .getRealPath(JConfig.getConfig(JConfig.FILEUPLOAD));
	        String originalFileName = file.getOriginalFilename();
	        String newFileName = res
					+ originalFileName.substring(originalFileName.lastIndexOf("."));
	        File targetFile = new File(path, newFileName);
	        if (!targetFile.exists()) {
	            targetFile.mkdirs();
	        }
	        file.transferTo(targetFile);
	        return targetFile;
	    }
}
