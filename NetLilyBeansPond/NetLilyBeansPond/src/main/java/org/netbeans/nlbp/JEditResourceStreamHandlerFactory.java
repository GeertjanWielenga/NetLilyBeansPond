package org.netbeans.nlbp;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import javax.imageio.ImageIO;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = URLStreamHandlerFactory.class)
public class JEditResourceStreamHandlerFactory implements URLStreamHandlerFactory {
    @Override
    public URLStreamHandler createURLStreamHandler(String protocol) {
        if (protocol.equals("jeditresource")) {
            return new LilyPondToolHandler();
        }
        return null;
    }
    private static class LilyPondToolHandler extends URLStreamHandler {
        @Override
        protected URLConnection openConnection(URL u) throws IOException {
            return new GeekOutConnection(u);
        }
    }
    private static class GeekOutConnection extends URLConnection {
        private ByteArrayInputStream is = null;
        public GeekOutConnection(URL url) throws UnsupportedEncodingException {
            super(url);
            String file = url.getPath().replace("/LilyPondTool.jar!/", "");
            Image image = ImageUtilities.loadImage(file);
            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write((RenderedImage) image, "png", os);
                is = new ByteArrayInputStream(os.toByteArray());
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        @Override
        public void connect() throws IOException {
        }
        @Override
        public InputStream getInputStream() throws IOException {
            return is;
        }
    }
}
