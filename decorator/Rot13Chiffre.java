import java.io.InputStream;

public class Rot13Chiffre extends CeaserChiffre {

    protected Rot13Chiffre(InputStream in) {
        super(in, 13);
    }
    
}
