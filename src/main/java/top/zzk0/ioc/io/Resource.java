package top.zzk0.ioc.io;

import java.io.IOException;
import java.io.InputStream;

/*
[interface] Resource: 只管输入流, getInputStream
         |
         V
    UrlResource

ResourceLoader: 返回一个 Resource,  具体实现依赖于 UrlResource, 方法中用到了具体的 URL
*/


public interface Resource {
    InputStream getInputStream() throws IOException;
}
