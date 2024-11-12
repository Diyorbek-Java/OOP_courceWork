import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLRepository {
    private final String xmlFilePath;

    public XMLRepository(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }

    // Create (add) product to the XML file
    public boolean saveProducts(Product product) {
        try {
            Document document = getDocument();

            Element root = document.getDocumentElement();

            // Create product element
            Element productElement = document.createElement("product");

            // Create and append name element
            Element nameElement = document.createElement("name");
            nameElement.appendChild(document.createTextNode(product.getName()));
            productElement.appendChild(nameElement);

            // Create and append price element
            Element priceElement = document.createElement("price");
            priceElement.appendChild(document.createTextNode(product.getPrice().toString()));
            productElement.appendChild(priceElement);

            // Append product element to root
            root.appendChild(productElement);

            saveDocument(document);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    // Read all products from the XML file
    public List<Product> loadProducts() {
        List<Product> productList = new ArrayList<>();
        try {
            Document document = getDocument();
            NodeList nodeList = document.getElementsByTagName("product");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element productElement = (Element) nodeList.item(i);
                String name = productElement.getElementsByTagName("name").item(0).getTextContent();
                Double price = Double.parseDouble(productElement.getElementsByTagName("price").item(0).getTextContent());

                productList.add(new Product(name, price));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }


    // Delete a product from the XML file by name
    public boolean deleteProduct(String productName) {
        try {
            Document document = getDocument();
            NodeList nodeList = document.getElementsByTagName("product");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element productElement = (Element) nodeList.item(i);
                String name = productElement.getElementsByTagName("name").item(0).getTextContent();

                if (name.equals(productName)) {
                    productElement.getParentNode().removeChild(productElement);
                    break;
                }
            }

            saveDocument(document);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    // Helper function to get the document (parse XML)
    private Document getDocument() throws Exception {
        File xmlFile = new File(xmlFilePath);
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        return documentBuilder.parse(xmlFile);
    }

    // Helper function to save the document back to the XML file
    private void saveDocument(Document document) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(xmlFilePath));
        transformer.transform(domSource, streamResult);
    }
}
