package app.application.ecobikerental.service.impl;
import com.google.zxing.WriterException;
import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
@Service
public class BarcodeGeneraterServiceImpl {
        private static final int WIDTH = 300;
        private static final int HEIGHT = 100;
        private static final String FORMAT = "png";

        public byte[] generateBarcode(String barcodeText) throws IOException, WriterException {
            MultiFormatWriter barcodeWriter = new MultiFormatWriter();
            BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.CODE_128, WIDTH, HEIGHT);

            BufferedImage barcodeImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    barcodeImage.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF);
                }
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(barcodeImage, FORMAT, baos);

            return baos.toByteArray();
        }
    }

