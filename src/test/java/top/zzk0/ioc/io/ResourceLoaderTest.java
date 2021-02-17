package top.zzk0.ioc.io;

import org.junit.jupiter.api.Test;

import java.io.*;

class ResourceLoaderTest {

    @Test
    public void testRead() throws IOException {
        ResourceLoader loader = new ResourceLoader();
        Resource resource = loader.getResource("tiny-spring.xml");
        InputStream inputStream = resource.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(reader);
        String line = br.readLine();
        while (line != null) {
            System.out.println(line);
            line = br.readLine();
        }
    }
}