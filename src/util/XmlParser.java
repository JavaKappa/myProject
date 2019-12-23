package util;


import storage.WebAppException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.Writer;

public class XmlParser {
    private final JAXBContext context;
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;


    public XmlParser(Class... classesToBeBound) {
        try {
            context = JAXBContext.newInstance(classesToBeBound);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e) {
            throw new WebAppException("JAXB init failed", e);
        }
    }

    public <T> T unmarshalling(Reader r) {
        try {
            return (T) unmarshaller.unmarshal(r);
        } catch (JAXBException e) {
            throw new WebAppException("Unmarshalling failed", e);
        }
    }

    public <T> void marshalling(Writer w, T obj) {
        try {
            marshaller.marshal(obj, w);
        } catch (JAXBException e) {
            throw new WebAppException("Marshalling failed", e);
        }
    }
}
