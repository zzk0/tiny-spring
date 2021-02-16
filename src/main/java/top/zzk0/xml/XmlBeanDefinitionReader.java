package top.zzk0.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import top.zzk0.ioc.BeanDefinition;
import top.zzk0.ioc.BeanReference;
import top.zzk0.ioc.PropertyValue;
import top.zzk0.io.Resource;
import top.zzk0.io.ResourceLoader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(ResourceLoader loader) {
        super(loader);
    }

    @Override
    public void loadBeanDefinition(String location) throws Exception {
        Resource resource = getLoader().getResource(location);
        doLoadBeanDefinition(resource.getInputStream());
    }

    private void doLoadBeanDefinition(InputStream inputStream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inputStream);
        parseBeanDefinitions(document);
        inputStream.close();
    }

    private void parseBeanDefinitions(Document document) {
        // root: <beans>...</beans>
        Element root = document.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName("bean");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                Element element = (Element)node;
                BeanDefinition definition = new BeanDefinition();
                String name = element.getAttribute("name");
                String className = element.getAttribute("class");
                definition.setClassName(className);
                parseBeanProperties(element, definition);
                getRegistry().put(name, definition);
            }
        }
    }

    private void parseBeanProperties(Element property, BeanDefinition definition) {
        NodeList nodeList = property.getElementsByTagName("property");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                Element element = (Element)node;
                String name = element.getAttribute("name");
                String value = element.getAttribute("value");
                if (value != null && value.length() > 0) {
                    definition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                }
                else {
                    String ref = element.getAttribute("ref");
                    definition.getPropertyValues().addPropertyValue(
                            new PropertyValue(name, new BeanReference(ref)));
                }
            }
        }
    }
}
