package cn.madeai;

import java.io.*;

/**
 * Created by <a href="mailto:huangyebiaoke@outlook.com">huang</a> on 2020/11/24 21:20
 */
public class WriteFile {
    public static void write(String path,String content) throws IOException {
        File file=new File(path.replace("\\", File.separator));
        OutputStream os=null;
        try {
            //true追加文字,false或null覆盖文件原本的;
            os=new FileOutputStream(file,true);
            os.write(content.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(os!=null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
