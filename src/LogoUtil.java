import javax.swing.*;
import java.awt.*;

public class LogoUtil {

    public static JLabel getLogoLabel() {

        ImageIcon icon = new ImageIcon("src/logo.png");

        Image image = icon.getImage().getScaledInstance(
                170,
                70,
                Image.SCALE_SMOOTH
        );

        JLabel logoLabel = new JLabel(new ImageIcon(image));

        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setVerticalAlignment(SwingConstants.CENTER);

        logoLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        5, 10, 5, 10
                )
        );

        return logoLabel;
    }
}